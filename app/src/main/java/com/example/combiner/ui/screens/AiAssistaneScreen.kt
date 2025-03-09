package com.example.combiner.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import com.example.combiner.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AiAssistantScreen() {
    var userInput by remember { mutableStateOf("") }
    var chatHistory by remember { mutableStateOf(listOf<String>()) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("AI Asistan", color = SoftIvory) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = WarmGray, // Üst bar kahverengi
                    titleContentColor = DeepChocolate
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(BeigeCream) // Arka plan bej yapıldı
                .padding(padding)
                .padding(16.dp)
        ) {
            Text(
                "Sana nasıl yardımcı olabilirim?",
                style = MaterialTheme.typography.titleMedium,
                color = DeepChocolate
            )

            Spacer(modifier = Modifier.height(16.dp))

            chatHistory.forEach { message ->
                Text(text = "🤖 $message", modifier = Modifier.padding(4.dp), color = DeepChocolate)
            }

            Spacer(modifier = Modifier.weight(1f))

            BasicTextField(
                value = userInput,
                onValueChange = { userInput = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .background(WarmGray, shape = MaterialTheme.shapes.medium) // Arka plan gri
                    .padding(12.dp),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Send),
                keyboardActions = KeyboardActions(onSend = {
                    if (userInput.isNotBlank()) {
                        chatHistory = chatHistory + userInput
                        userInput = ""
                    }
                })
            )
        }
    }
}
