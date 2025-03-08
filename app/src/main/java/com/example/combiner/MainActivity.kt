package com.example.combiner

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.combiner.ui.screens.MainScreen
import com.example.combiner.ui.theme.CombinerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CombinerTheme {
                MainScreen()
            }
        }
    }
}
