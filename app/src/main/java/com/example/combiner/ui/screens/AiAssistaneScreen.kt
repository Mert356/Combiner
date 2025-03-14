package com.example.combiner.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.combiner.ui.theme.SecondaryTextAndIconColor
import com.example.combiner.ui.theme.TextAndIconColor

@Composable
fun AiAssistantScreen(viewModel: AiAssistantViewModel = viewModel()) {
    var userMessage by remember { mutableStateOf("") }
    var chatHistory by remember { mutableStateOf(listOf<Message>()) }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(chatHistory) { message ->
                ChatBubble(message)
            }
        }

        Row(modifier = Modifier.fillMaxWidth().padding(top = 8.dp)) {
            OutlinedTextField(
                value = userMessage,
                onValueChange = { userMessage = it },
                modifier = Modifier.weight(1f),
                placeholder = { Text("Mesajınızı yazın...") }
            )

            Button(
                onClick = {
                    if (userMessage.isNotBlank()) {
                        val tempMessage = userMessage
                        userMessage = ""
                        viewModel.sendMessage(tempMessage) { response ->
                            chatHistory = chatHistory + Message("user", tempMessage) + Message("assistant", response)
                        }
                    }
                },
                modifier = Modifier.padding(start = 8.dp)
            ) {
                Text("Gönder")
            }
        }
    }
}

@Composable
fun ChatBubble(message: Message) {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = if (message.role == "user") Alignment.CenterEnd else Alignment.CenterStart
    ) {
        Surface(
            modifier = Modifier.padding(8.dp),
            shape = MaterialTheme.shapes.medium,
            color = if (message.role == "user") SecondaryTextAndIconColor else SecondaryTextAndIconColor
        ) {
            Text(
                text = message.content,
                color = TextAndIconColor,
                modifier = Modifier.padding(12.dp)
            )
        }
    }
}

