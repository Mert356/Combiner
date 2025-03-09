package com.example.combiner.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.combiner.ui.components.SpecialTextField
import com.example.combiner.ui.theme.BeigeCream
import com.google.firebase.auth.FirebaseAuth


@Composable
fun SignUpScreen(navController: NavController) {
    val name = remember { mutableStateOf("") }
    val surname = remember { mutableStateOf("") }
    val email = remember { mutableStateOf("") }
    val password1 = remember { mutableStateOf("") }
    val password2 = remember { mutableStateOf("") }
    val errorMessage = remember { mutableStateOf<String?>(null) }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(BeigeCream)
            .padding(10.dp)
    ) {
        SpecialTextField(name.value, { name.value = it }, "Your Name")
        SpecialTextField(surname.value, { surname.value = it }, "Your Surname")
        SpecialTextField(email.value, { email.value = it }, "Your E-mail")
        SpecialTextField(password1.value, { password1.value = it }, "Your Password", true)
        SpecialTextField(password2.value, { password2.value = it }, "Confirm Password", true)

        errorMessage.value?.let {
            Text(text = it, color = Color.Red, textAlign = TextAlign.Center)
        }

        Button(
            onClick = {
                if (password1.value == password2.value &&
                    name.value.isNotEmpty() &&
                    surname.value.isNotEmpty() &&
                    email.value.isNotEmpty() &&
                    password1.value.isNotEmpty()
                ) {
                    registerUser(email.value, password1.value) { success, error ->
                        if (success) {
                            navController.navigate("main") {
                                popUpTo("log_in") { inclusive = true } // Önceki sayfaları temizle
                            }
                        } else {
                            errorMessage.value = error
                        }
                    }
                } else {
                    errorMessage.value = "Please fill in all fields and make sure the passwords match!"
                }
            },
            modifier = Modifier
                .padding(40.dp)
                .fillMaxWidth()
        ) {
            Text("Sign Up")
        }
    }
}

fun registerUser(email: String, password: String, onResult: (Boolean, String?) -> Unit) {
    val auth = FirebaseAuth.getInstance()

    auth.createUserWithEmailAndPassword(email, password)
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                onResult(true, null)
            } else {
                onResult(false, task.exception?.message)
            }
        }
}
