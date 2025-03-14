package com.example.combiner.ui.screens

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AiAssistantViewModel : ViewModel() {
    private val job = SupervisorJob()

    var chatHistory = mutableListOf<Message>()
        private set

    fun sendMessage(userMessage: String, onResponse: (String) -> Unit) {
        val newUserMessage = Message("user", userMessage)
        chatHistory.add(newUserMessage)

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = RetrofitClient.api.getChatResponse(
                    ChatRequest("gpt-4", chatHistory)
                )
                val aiResponse = response.choices.firstOrNull()?.message?.content ?: "Yanıt alınamadı."

                withContext(Dispatchers.Main) {
                    chatHistory.add(Message("assistant", aiResponse))
                    onResponse(aiResponse)
                }
            } catch (e: Exception) {
                Log.e("AiAssistantViewModel", "API çağrısı başarısız: ${e.message}")
                e.printStackTrace()
                withContext(Dispatchers.Main) {
                    onResponse("Bir hata oluştu, lütfen tekrar deneyin.")
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}
