package com.example.combiner.ui.screens.tflite

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ChatBotViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ChatBotViewModel::class.java)) {
            return ChatBotViewModel(context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
