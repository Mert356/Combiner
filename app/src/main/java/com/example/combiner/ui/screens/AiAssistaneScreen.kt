package com.example.combiner.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.combiner.ui.theme.BackgroundColor
import com.example.combiner.ui.theme.PostBackgroundColor
import com.example.combiner.ui.theme.SecondaryTextAndIconColor
import com.example.combiner.ui.theme.TextAndIconColor
import com.google.ai.client.generativeai.type.Content
import com.google.ai.client.generativeai.type.TextPart
import com.google.ai.client.generativeai.type.asTextOrNull
import androidx.compose.runtime.Composable


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AiAssistantScreen(viewModel: AiAssistantViewModel = viewModel()) {
    var userMessage by remember { mutableStateOf("") }
    var chatHistory by remember { mutableStateOf<List<Content>>(emptyList()) }
    val keyboardController = LocalSoftwareKeyboardController.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Chatbot") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = BackgroundColor,
                    titleContentColor = TextAndIconColor,
                    actionIconContentColor = TextAndIconColor
                ),
                actions = {
                    Icon(
                        imageVector = Icons.Default.Info,
                        contentDescription = "Chat Icon",
                        modifier = Modifier.padding(16.dp)
                    )
                }
            )
        },
        containerColor = BackgroundColor
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            LazyColumn(modifier = Modifier.weight(1f)) {
                items(chatHistory) { message ->
                    ChatBubble(message)
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                OutlinedTextField(
                    value = userMessage,
                    onValueChange = { newText ->
                        if ('\n' !in newText) {
                            userMessage = newText
                        }
                    },
                    modifier = Modifier.weight(1f).padding(4.dp).fillMaxWidth(),
                    placeholder = { Text("Enter your message...") },
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Send
                    ),
                    keyboardActions = KeyboardActions(
                        onSend = {
                            if (userMessage.isNotBlank()) {
                                sendMessage(viewModel, userMessage, chatHistory) { updatedChat ->
                                    chatHistory = updatedChat
                                }
                                userMessage = ""
                                keyboardController?.hide()
                            }
                        }
                    ),
                    maxLines = 5,
                )

                Button(
                    onClick = {
                        if (userMessage.isNotBlank()) {
                            sendMessage(viewModel, userMessage, chatHistory) { updatedChat ->
                                chatHistory = updatedChat
                            }
                            userMessage = ""
                            keyboardController?.hide()
                        }
                    },
                    modifier = Modifier.height(50.dp)
                        .padding(4.dp)
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
            color = if (message.role == "user") SecondaryTextAndIconColor else PostBackgroundColor
        ) {
            Text(
                text = message.parts.firstOrNull()?.asTextOrNull() ?: "",
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.padding(12.dp)
            )
        }
    }
}




fun sendMessage(
    viewModel: AiAssistantViewModel,
    userMessage: String,
    chatHistory: List<Content>,
    updateChatHistory: (List<Content>) -> Unit
) {
    if (userMessage.isNotBlank()) {
        val tempMessage = userMessage

        viewModel.sendMessage(tempMessage) { response ->
            updateChatHistory(
                chatHistory + listOf(
                    Content(role = "user", parts = listOf(TextPart(tempMessage))),
                    Content(role = "assistant", parts = listOf(TextPart(response)))
                )
            )
        }
    }
}
