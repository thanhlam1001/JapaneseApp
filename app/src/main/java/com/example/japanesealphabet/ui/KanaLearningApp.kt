@file:OptIn(
    androidx.compose.foundation.layout.ExperimentalLayoutApi::class,
    androidx.compose.material3.ExperimentalMaterial3Api::class
)

package com.example.japanesealphabet.ui

import android.speech.tts.TextToSpeech
import android.speech.tts.UtteranceProgressListener
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AutoAwesome
import androidx.compose.material.icons.rounded.RecordVoiceOver
import androidx.compose.material.icons.rounded.School
import androidx.compose.material.icons.rounded.Translate
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.japanesealphabet.data.KanaCategory
import com.example.japanesealphabet.data.KanaItem
import com.example.japanesealphabet.data.KanaRepository
import com.example.japanesealphabet.data.KanaScript
import com.example.japanesealphabet.ui.theme.JapaneseAlphabetTheme
import java.util.Locale
import kotlin.random.Random

private enum class LearningMode(val label: String) {
    STUDY("Study cards"),
    QUIZ("Quiz")
}

private data class QuizQuestion(
    val prompt: KanaItem,
    val options: List<KanaItem>
)

@Composable
fun KanaLearningApp() {
    var selectedScript by rememberSaveable { mutableStateOf(KanaScript.HIRAGANA) }
    var selectedCategory by rememberSaveable { mutableStateOf(KanaCategory.BASIC) }
    var learningMode by rememberSaveable { mutableStateOf(LearningMode.STUDY) }
    val speakerState = rememberKanaSpeaker()

    val availableCategories = remember(selectedScript) {
        KanaRepository.availableCategories(selectedScript)
    }

    LaunchedEffect(selectedScript, availableCategories) {
        if (selectedCategory !in availableCategories) {
            selectedCategory = availableCategories.first()
        }
    }

    val kanaList = remember(selectedScript, selectedCategory) {
        KanaRepository.byScriptAndCategory(selectedScript, selectedCategory)
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Kana Master") }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(innerPadding)
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                HeaderCard(
                    selectedScript = selectedScript,
                    selectedCategory = selectedCategory,
                    total = kanaList.size,
                    pronunciationReady = speakerState.isReady
                )
            }
            item {
                ScriptSelector(
                    selectedScript = selectedScript,
                    onScriptChange = {
                        selectedScript = it
                        selectedCategory = KanaRepository.availableCategories(it).first()
                    }
                )
            }
            item {
                CategorySelector(
                    selectedScript = selectedScript,
                    selectedCategory = selectedCategory,
                    onCategoryChange = { selectedCategory = it }
                )
            }
            item {
                ModeSelector(
                    learningMode = learningMode,
                    onModeChange = { learningMode = it }
                )
            }
            item {
                StatsRow(kanaList = kanaList, selectedCategory = selectedCategory)
            }
            item {
                if (learningMode == LearningMode.STUDY) {
                    StudySection(
                        kanaList = kanaList,
                        pronunciationReady = speakerState.isReady,
                        onPronounce = { speakerState.speak(it.symbol) }
                    )
                } else {
                    QuizSection(kanaList = kanaList)
                }
            }
            item {
                Spacer(Modifier.height(8.dp))
            }
        }
    }
}

private data class KanaSpeakerState(
    val isReady: Boolean,
    val speak: (String) -> Unit
)

@Composable
private fun rememberKanaSpeaker(): KanaSpeakerState {
    val context = LocalContext.current
    var tts by remember { mutableStateOf<TextToSpeech?>(null) }
    var isReady by remember { mutableStateOf(false) }
    val latestReadyState by rememberUpdatedState(isReady)

    DisposableEffect(context) {
        var disposed = false
        var engine: TextToSpeech? = null
        engine = TextToSpeech(context) { status ->
            if (!disposed && status == TextToSpeech.SUCCESS) {
                val languageResult = engine?.setLanguage(Locale.JAPAN) ?: TextToSpeech.LANG_NOT_SUPPORTED
                isReady = languageResult != TextToSpeech.LANG_MISSING_DATA &&
                    languageResult != TextToSpeech.LANG_NOT_SUPPORTED
                if (isReady) {
                    engine?.setPitch(1.0f)
                    engine?.setSpeechRate(0.9f)
                    engine?.setOnUtteranceProgressListener(object : UtteranceProgressListener() {
                        override fun onStart(utteranceId: String?) = Unit
                        override fun onDone(utteranceId: String?) = Unit
                        @Deprecated("Deprecated in Java")
                        override fun onError(utteranceId: String?) = Unit
                    })
                }
            } else if (!disposed) {
                isReady = false
            }
        }
        tts = engine

        onDispose {
            disposed = true
            tts = null
            engine?.stop()
            engine?.shutdown()
        }
    }

    return KanaSpeakerState(
        isReady = isReady,
        speak = { text ->
            if (latestReadyState) {
                tts?.speak(text, TextToSpeech.QUEUE_FLUSH, null, "kana-$text")
            }
        }
    )
}

@Composable
private fun HeaderCard(
    selectedScript: KanaScript,
    selectedCategory: KanaCategory,
    total: Int,
    pronunciationReady: Boolean
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 12.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        shape = RoundedCornerShape(28.dp)
    ) {
        Column(
            modifier = Modifier.padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Icon(
                    imageVector = Icons.Rounded.School,
                    contentDescription = null,
                    modifier = Modifier.size(28.dp)
                )
                Text(
                    text = "Learn Japanese kana by group",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
            }
            Text(
                text = selectedScript.subtitle,
                style = MaterialTheme.typography.bodyLarge
            )
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                AssistChip(onClick = { }, label = { Text("$total cards") })
                AssistChip(onClick = { }, label = { Text(selectedCategory.title) })
                AssistChip(
                    onClick = { },
                    label = { Text(if (pronunciationReady) "Pronunciation ready" else "Pronunciation unavailable") }
                )
            }
        }
    }
}

@Composable
private fun ScriptSelector(
    selectedScript: KanaScript,
    onScriptChange: (KanaScript) -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
        Text(
            text = "Choose script",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold
        )
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            KanaScript.entries.forEach { script ->
                FilterChip(
                    selected = script == selectedScript,
                    onClick = { onScriptChange(script) },
                    label = { Text(script.title) },
                    leadingIcon = if (script == selectedScript) {
                        { Icon(Icons.Rounded.Translate, contentDescription = null) }
                    } else {
                        null
                    }
                )
            }
        }
    }
}

@Composable
private fun CategorySelector(
    selectedScript: KanaScript,
    selectedCategory: KanaCategory,
    onCategoryChange: (KanaCategory) -> Unit
) {
    val categories = remember(selectedScript) {
        KanaRepository.availableCategories(selectedScript)
    }

    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
        Text(
            text = "Choose learning group",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold
        )
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            categories.forEach { category ->
                FilterChip(
                    selected = category == selectedCategory,
                    onClick = { onCategoryChange(category) },
                    label = { Text(category.title) }
                )
            }
        }
        Text(
            text = selectedCategory.description,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun ModeSelector(
    learningMode: LearningMode,
    onModeChange: (LearningMode) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        LearningMode.entries.forEach { mode ->
            Surface(
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(22.dp),
                tonalElevation = if (mode == learningMode) 4.dp else 0.dp,
                color = if (mode == learningMode) {
                    MaterialTheme.colorScheme.secondaryContainer
                } else {
                    MaterialTheme.colorScheme.surfaceVariant
                },
                onClick = { onModeChange(mode) }
            ) {
                Row(
                    modifier = Modifier.padding(vertical = 14.dp, horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    if (mode == LearningMode.QUIZ) {
                        Icon(
                            imageVector = Icons.Rounded.AutoAwesome,
                            contentDescription = null,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(Modifier.width(6.dp))
                    }
                    Text(
                        text = mode.label,
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    }
}

@Composable
private fun StatsRow(
    kanaList: List<KanaItem>,
    selectedCategory: KanaCategory
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        StatCard(
            modifier = Modifier.weight(1f),
            label = "Cards",
            value = kanaList.size.toString()
        )
        StatCard(
            modifier = Modifier.weight(1f),
            label = "Rows",
            value = kanaList.map { it.row }.distinct().size.toString()
        )
        StatCard(
            modifier = Modifier.weight(1f),
            label = "Type",
            value = when (selectedCategory) {
                KanaCategory.BASIC -> "Core"
                KanaCategory.DAKUTEN -> "Voice"
                KanaCategory.YOUON -> "Blend"
            }
        )
    }
}

@Composable
private fun StatCard(
    modifier: Modifier = Modifier,
    label: String,
    value: String
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = value,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = label,
                style = MaterialTheme.typography.labelLarge,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
private fun StudySection(
    kanaList: List<KanaItem>,
    pronunciationReady: Boolean,
    onPronounce: (KanaItem) -> Unit
) {
    var currentIndex by rememberSaveable(kanaList) { mutableIntStateOf(0) }
    if (currentIndex > kanaList.lastIndex) currentIndex = 0
    val item = kanaList[currentIndex]

    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(32.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface
            )
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = "Character ${currentIndex + 1}/${kanaList.size}",
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = item.symbol,
                    style = MaterialTheme.typography.displayLarge,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = item.romaji,
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = "Row ${item.row}",
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    text = item.example,
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center
                )
                OutlinedButton(
                    onClick = { onPronounce(item) },
                    enabled = pronunciationReady,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(
                        imageVector = Icons.Rounded.RecordVoiceOver,
                        contentDescription = null
                    )
                    Spacer(Modifier.width(8.dp))
                    Text(if (pronunciationReady) "Play pronunciation" else "Japanese TTS unavailable")
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    OutlinedButton(
                        onClick = {
                            currentIndex =
                                if (currentIndex == 0) kanaList.lastIndex else currentIndex - 1
                        },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Previous")
                    }
                    Button(
                        onClick = {
                            currentIndex =
                                if (currentIndex == kanaList.lastIndex) 0 else currentIndex + 1
                        },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Next")
                    }
                }
            }
        }

        Text(
            text = "Quick overview",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold
        )
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            kanaList.forEachIndexed { index, kana ->
                KanaMiniChip(
                    kana = kana,
                    selected = index == currentIndex,
                    onClick = { currentIndex = index }
                )
            }
        }
    }
}

@Composable
private fun KanaMiniChip(
    kana: KanaItem,
    selected: Boolean,
    onClick: () -> Unit
) {
    Surface(
        onClick = onClick,
        shape = RoundedCornerShape(18.dp),
        color = if (selected) {
            MaterialTheme.colorScheme.primary
        } else {
            MaterialTheme.colorScheme.surface
        },
        border = BorderStroke(
            1.dp,
            if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outlineVariant
        )
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 14.dp, vertical = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = kana.symbol,
                style = MaterialTheme.typography.titleLarge,
                color = if (selected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = kana.romaji,
                style = MaterialTheme.typography.labelMedium,
                color = if (selected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun QuizSection(kanaList: List<KanaItem>) {
    var score by rememberSaveable(kanaList) { mutableIntStateOf(0) }
    var answered by remember(kanaList) { mutableStateOf(false) }
    var selectedOption by remember(kanaList) { mutableStateOf<KanaItem?>(null) }
    var question by remember(kanaList) { mutableStateOf(buildQuestion(kanaList)) }

    val correct = selectedOption == question.prompt

    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(28.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.tertiaryContainer
            )
        ) {
            Column(
                modifier = Modifier.padding(22.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "Current score: $score",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Choose the correct kana for the romaji below.",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(28.dp)
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = "Romaji",
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = question.prompt.romaji,
                    style = MaterialTheme.typography.displaySmall,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        question.options.forEach { option ->
            val isSelected = option == selectedOption
            val isCorrectAnswer = option == question.prompt
            val containerColor = when {
                !answered && isSelected -> MaterialTheme.colorScheme.secondaryContainer
                answered && isCorrectAnswer -> MaterialTheme.colorScheme.primaryContainer
                answered && isSelected -> MaterialTheme.colorScheme.errorContainer
                else -> MaterialTheme.colorScheme.surface
            }

            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(22.dp),
                color = containerColor,
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
                onClick = {
                    if (!answered) {
                        selectedOption = option
                    }
                }
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 18.dp, vertical = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(42.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.surfaceVariant),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = option.symbol,
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Spacer(Modifier.width(14.dp))
                    Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                        Text(
                            text = option.example,
                            style = MaterialTheme.typography.bodyLarge
                        )
                        Text(
                            text = "Row ${option.row}",
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            OutlinedButton(
                onClick = {
                    score = 0
                    answered = false
                    selectedOption = null
                    question = buildQuestion(kanaList)
                },
                modifier = Modifier.weight(1f)
            ) {
                Text("Reset")
            }
            Button(
                onClick = {
                    if (!answered && selectedOption != null) {
                        answered = true
                        if (correct) score += 1
                    } else {
                        answered = false
                        selectedOption = null
                        question = buildQuestion(kanaList)
                    }
                },
                modifier = Modifier.weight(1f)
            ) {
                Text(if (!answered) "Check answer" else "Next question")
            }
        }

        if (answered) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = if (correct) {
                        MaterialTheme.colorScheme.primaryContainer
                    } else {
                        MaterialTheme.colorScheme.errorContainer
                    }
                )
            ) {
                Text(
                    text = if (correct) {
                        "Correct. ${question.prompt.symbol} is pronounced ${question.prompt.romaji}."
                    } else {
                        "Not quite. The correct answer is ${question.prompt.symbol} (${question.prompt.romaji})."
                    },
                    modifier = Modifier.padding(18.dp),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}

private fun buildQuestion(kanaList: List<KanaItem>): QuizQuestion {
    val prompt = kanaList.random(Random.Default)
    val distractors = kanaList
        .filterNot { it == prompt }
        .shuffled(Random.Default)
        .take(minOf(3, kanaList.lastIndex))
    return QuizQuestion(
        prompt = prompt,
        options = (distractors + prompt).shuffled(Random.Default)
    )
}

@Preview(showBackground = true)
@Composable
private fun KanaLearningAppPreview() {
    JapaneseAlphabetTheme {
        KanaLearningApp()
    }
}
