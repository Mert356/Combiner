package com.example.combiner.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.combiner.ui.theme.BackgroundColor
import com.example.combiner.ui.theme.TextAndIconColor
import com.google.ai.client.generativeai.type.Content
import com.google.ai.client.generativeai.type.TextPart
import com.google.ai.client.generativeai.type.asTextOrNull

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AiAssistantScreen(viewModel: AiAssistantViewModel = viewModel()) {
    var userMessage by remember { mutableStateOf("") }
    var chatHistory by remember { mutableStateOf<List<Content>>(emptyList()) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Chatbot") },
                colors = TopAppBarColors(
                    containerColor = BackgroundColor,
                    scrolledContainerColor = BackgroundColor,
                    navigationIconContentColor = TextAndIconColor,
                    titleContentColor = TextAndIconColor,
                    actionIconContentColor = TextAndIconColor,
                ),
                actions = {
                    Icon(
                        imageVector = Icons.Default.Info,
                        contentDescription = "Chat Icon",
                        modifier = Modifier.padding(16.dp)
                    )
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues) // Scaffold'un padding değerlerini uygula
                .padding(16.dp)
        ) {
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
                    placeholder = { Text("Enter your message...") }
                )

                Button(
                    onClick = {
                        if (userMessage.isNotBlank()) {
                            val tempMessage = userMessage
                            userMessage = ""
                            viewModel.sendMessage(tempMessage) { response ->
                                chatHistory = chatHistory +
                                        Content(role = "user", parts = listOf(TextPart(tempMessage))) +
                                        Content(role = "assistant", parts = listOf(TextPart(response)))
                            }
                        }
                    },
                    modifier = Modifier.padding(start = 8.dp)
                ) {
                    Text("Send")
                }
            }
        }
    }
}

@Composable
fun ChatBubble(message: Content) {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = if (message.role == "user") Alignment.CenterEnd else Alignment.CenterStart
    ) {
        Surface(
            modifier = Modifier.padding(8.dp),
            shape = MaterialTheme.shapes.medium,
            color = if (message.role == "user") MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary
        ) {
            Text(
                text = message.parts.firstOrNull()?.asTextOrNull() ?: "",
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.padding(12.dp)
            )
        }
    }
}
