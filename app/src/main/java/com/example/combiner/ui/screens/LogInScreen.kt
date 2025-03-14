package com.example.combiner.ui.screens

import Google
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.*
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.*
import androidx.navigation.NavController
import com.example.combiner.BuildConfig
import com.example.combiner.ui.components.SpecialButton
import com.example.combiner.ui.components.SpecialTextField
import com.example.combiner.ui.theme.TextAndIconColor
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

@Composable
fun LogInScreen(navController: NavController) {
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val errorMessage = remember { mutableStateOf<String?>(null) }

    val auth = FirebaseAuth.getInstance()
    val context = LocalContext.current
    val activity = context as? ComponentActivity
    val googleClientId = BuildConfig.GOOGLE_CLIENT_ID
    val googleSignInClient = remember {
        activity?.let {
            val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(googleClientId)
                .requestEmail()
                .build()
            GoogleSignIn.getClient(it, gso)
        }
    }

    val googleSignInLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.data == null) {
            errorMessage.value = "Google Girişi İptal Edildi veya Hata Oluştu"
            return@rememberLauncherForActivityResult
        }

        val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
        try {
            val account = task.getResult(ApiException::class.java)
            val credential = GoogleAuthProvider.getCredential(account.idToken, null)

            auth.signInWithCredential(credential)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        navController.navigate("main") {
                            popUpTo("log_in") { inclusive = true }
                        }
                    } else {
                        errorMessage.value = "Google Girişi Başarısız: ${task.exception?.localizedMessage}"
                    }
                }
        } catch (e: ApiException) {
            errorMessage.value = "Google Girişi Hatası: ${e.message}"
        }
    }

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
            color = TextAndIconColor,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 50.dp)
        )

        Column(
            modifier = Modifier
                .width(300.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SpecialTextField(email.value, { email.value = it }, "Enter Your E-mail")
            SpecialTextField(password.value, { password.value = it }, "Enter Your Password", true)

            errorMessage.value?.let {
                Text(text = it, color = Color.Red, textAlign = TextAlign.Center)
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                SpecialButton("Sign In") {
                    if (email.value.isEmpty() || password.value.isEmpty()) {
                        errorMessage.value = "Please enter your email and password!"
                    } else {
                        loginUser(email.value, password.value) { success, _ ->
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

            DividerWithText()

            Button(
                onClick = {
                    googleSignInClient?.signInIntent?.let { signInIntent ->
                        googleSignInLauncher.launch(signInIntent)
                    } ?: run {
                        errorMessage.value = "Google Sign-In Başlatılamadı"
                    }
                },
                modifier = Modifier.fillMaxWidth().padding(10.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(5.dp)
                ) {
                    Icon(
                        imageVector = Google,
                        contentDescription = "Google Icon",
                        tint = TextAndIconColor,
                        modifier = Modifier.padding(end = 10.dp)
                    )
                    Text("Sign in with Google")
                }
            }
        }
    }
}


@Composable
fun DividerWithText() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        HorizontalDivider(
            modifier = Modifier
                .weight(1f)
                .height(1.dp),
            color = Color.Gray
        )
        Text(
            text = "Or Sign In with Google",
            modifier = Modifier.padding(horizontal = 8.dp),
            fontSize = 14.sp,
            color = TextAndIconColor
        )
        HorizontalDivider(
            modifier = Modifier
                .weight(1f)
                .height(1.dp),
            color = Color.Gray
        )
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
