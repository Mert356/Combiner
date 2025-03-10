package com.example.combiner.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.*
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.*
import androidx.navigation.NavController
import com.example.combiner.ui.components.SpecialButton
import com.example.combiner.ui.components.SpecialTextField
import com.example.combiner.ui.theme.DeepChocolate
import com.google.firebase.auth.FirebaseAuth

@Composable
fun LogInScreen(navController: NavController) {
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val errorMessage = remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.secondary),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "Welcome to OUTFIT COMBINER",
            fontSize = 50.sp,
            lineHeight = 60.sp,
            fontStyle = FontStyle.Italic,
            color = DeepChocolate,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 50.dp)
        )

        SpecialTextField(email.value, { email.value = it }, "Enter Your E-mail")
        SpecialTextField(password.value, { password.value = it }, "Enter Your Password", true)

        errorMessage.value?.let {
            Text(text = it, color = Color.Red, textAlign = TextAlign.Center)
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            SpecialButton("Log In") {
                if (email.value.isEmpty() || password.value.isEmpty()) {
                    errorMessage.value = "Please enter your email and password!"
                } else {
                    loginUser(email.value, password.value) { success, error ->
                        if (success) {
                            navController.navigate("main") {
                                popUpTo("log_in") { inclusive = true }
                            }
                        } else {
                            errorMessage.value = "Please check your E-mail and Password!"
                        }
                    }
                }
            }

            SpecialButton("Sign Up") {
                navController.navigate("sign_up") {
                    popUpTo("log_in") { inclusive = true }
                }
            }
        }
    }
}

fun loginUser(email: String, password: String, onResult: (Boolean, String?) -> Unit) {
    val auth = FirebaseAuth.getInstance()

    auth.signInWithEmailAndPassword(email, password)
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                onResult(true, null)
            } else {
                onResult(false, task.exception?.message)
            }
        }
}
