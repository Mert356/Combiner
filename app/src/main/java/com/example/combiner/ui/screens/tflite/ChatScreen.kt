package com.example.combiner.ui.screens.tflite

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp

@Composable
fun ChatScreen(viewModel: ChatBotViewModel) {
    var userInput by remember { mutableStateOf(TextFieldValue("")) }
    var chatHistory by remember { mutableStateOf(listOf<String>()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Mesajları gösteren alan
        Column(
            modifier = Modifier.weight(1f)
        ) {
            chatHistory.forEach { message ->
                Text(
                    text = message,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }

        // Kullanıcı giriş alanı
        OutlinedTextField(
            value = userInput,
            onValueChange = { userInput = it },
            placeholder = { Text("Mesajınızı yazın...") },
            modifier = Modifier.fillMaxWidth()
        )

        // Gönder butonu
        Button(
            onClick = {
                val userMessage = "Sen: ${userInput.text}"
                val botResponse = viewModel.getResponse(userInput.text)

                chatHistory = chatHistory + userMessage + botResponse
                userInput = TextFieldValue("") // Giriş alanını temizle
            },
            modifier = Modifier
                .align(Alignment.End)
                .padding(top = 8.dp)
        ) {
            Text("Gönder")
        }
    }
}
