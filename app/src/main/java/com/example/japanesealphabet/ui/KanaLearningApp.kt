@file:OptIn(
    androidx.compose.foundation.layout.ExperimentalLayoutApi::class,
    androidx.compose.material3.ExperimentalMaterial3Api::class
)

package com.example.japanesealphabet.ui

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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.japanesealphabet.data.KanaCategory
import com.example.japanesealphabet.data.KanaItem
import com.example.japanesealphabet.data.KanaRepository
import com.example.japanesealphabet.data.KanaScript
import com.example.japanesealphabet.ui.theme.JapaneseAlphabetTheme
import kotlin.random.Random

private enum class LearningMode(val label: String) {
    STUDY("Hoc the"),
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
                    total = kanaList.size
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
                    StudySection(kanaList = kanaList)
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

@Composable
private fun HeaderCard(
    selectedScript: KanaScript,
    selectedCategory: KanaCategory,
    total: Int
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
                    text = "Hoc bang chu cai Nhat tung nhom",
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
                AssistChip(onClick = { }, label = { Text("$total muc") })
                AssistChip(onClick = { }, label = { Text(selectedCategory.title) })
                AssistChip(onClick = { }, label = { Text("Hoc + Quiz") })
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
            text = "Chon bo chu",
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
            text = "Chon nhom hoc",
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
            label = "So the",
            value = kanaList.size.toString()
        )
        StatCard(
            modifier = Modifier.weight(1f),
            label = "So hang",
            value = kanaList.map { it.row }.distinct().size.toString()
        )
        StatCard(
            modifier = Modifier.weight(1f),
            label = "Nhom",
            value = when (selectedCategory) {
                KanaCategory.BASIC -> "Nen"
                KanaCategory.DAKUTEN -> "Bien"
                KanaCategory.YOUON -> "Ghep"
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
private fun StudySection(kanaList: List<KanaItem>) {
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
                    text = "Ky tu ${currentIndex + 1}/${kanaList.size}",
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
                    text = "Hang ${item.row}",
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    text = item.example,
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center
                )
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
                        Text("Lui")
                    }
                    Button(
                        onClick = {
                            currentIndex =
                                if (currentIndex == kanaList.lastIndex) 0 else currentIndex + 1
                        },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Tiep")
                    }
                }
            }
        }

        Text(
            text = "Tong quan nhanh",
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
                    text = "Diem hien tai: $score",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Chon ky tu dung voi romaji ben duoi.",
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
                            text = "Hang ${option.row}",
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
                Text("Lam moi")
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
                Text(if (!answered) "Cham diem" else "Cau tiep")
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
                        "Chinh xac. ${question.prompt.symbol} doc la ${question.prompt.romaji}."
                    } else {
                        "Chua dung. Dap an la ${question.prompt.symbol} (${question.prompt.romaji})."
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
