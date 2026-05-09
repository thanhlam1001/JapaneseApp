package com.example.japanesealphabet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.japanesealphabet.ui.KanaLearningApp
import com.example.japanesealphabet.ui.theme.JapaneseAlphabetTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JapaneseAlphabetTheme {
                KanaLearningApp()
            }
        }
    }
}
