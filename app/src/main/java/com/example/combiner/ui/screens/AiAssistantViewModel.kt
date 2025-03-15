package com.example.combiner.ui.screens

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.Content
import com.google.ai.client.generativeai.type.TextPart
import com.example.combiner.BuildConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AiAssistantViewModel : ViewModel() {
    private val generativeModel = GenerativeModel(
        modelName = "gemini-2.0-flash",
        apiKey = BuildConfig.API_KEY
    )

    var chatHistory = mutableListOf<Content>()
        private set

    fun sendMessage(userMessage: String, onResponse: (String) -> Unit) {
        val newUserMessage = Content(role = "user", parts = listOf(TextPart(userMessage)))
        chatHistory.add(newUserMessage)

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = generativeModel.generateContent(
                    Content(parts = listOf(TextPart(userMessage)))
                )

                val aiResponse = response.text ?: "Couldn't get a respond."

                withContext(Dispatchers.Main) {
                    onResponse(aiResponse)
                    chatHistory.add(Content(role = "assistant", parts = listOf(TextPart(aiResponse))))
                }
            } catch (e: Exception) {
                Log.e("AiAssistantViewModel", "Hata: ${e.message}")
                withContext(Dispatchers.Main) {
                    onResponse("Error occurred: ${e.message}")
                }
            }
        }
    }
}
