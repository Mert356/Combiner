package com.example.combiner.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.combiner.R
import com.example.combiner.ui.components.PostItem
import com.example.combiner.ui.theme.*
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch
import android.os.Handler
import android.os.Looper
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun HomeScreen(navController: NavHostController) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        scrimColor = Color.Transparent,
        drawerContent = {
            ModalDrawerSheet(
                modifier = Modifier
                    .width(280.dp)
                    .background(PostBackgroundColor)
            ) {
                DrawerContent(navController, onClose = {
                    scope.launch { drawerState.close() }
                })
            }
        }
    ) {
        Scaffold(
            topBar = {
                HomeTopBar(onMenuClick = {
                    scope.launch { drawerState.open() }
                })
            },
        ) { padding ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(BackgroundColor)
                    .padding(padding)
            ) {
                items(fakePosts) { post ->
                    PostItem(
                        username = post.username,
                        userProfileImage = post.image2,
                        postImage = post.image,
                        postDescription = post.description,
                        onLikeClick = { /* Like action */ },
                        onCommentClick = { /* Comment action */ },
                        onSaveClick = { /* Save action */ }
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopBar(onMenuClick: () -> Unit) {
    TopAppBar(
        title = { Text("Home") },
        navigationIcon = {
            IconButton(onClick = onMenuClick) {
                Icon(Icons.Filled.Menu, contentDescription = "Menu", tint = TextAndIconColor)
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = BackgroundColor,
            titleContentColor = TextAndIconColor
        )
    )
}

@Composable
fun DrawerContent(navController: NavHostController, onClose: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .width(280.dp)
            .background(BackgroundColor)
            .padding(16.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "My Profile",
            style = MaterialTheme.typography.titleLarge.copy(fontSize = 30.sp),
            color = TextAndIconColor,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Spacer(modifier = Modifier.padding(20.dp))
        DrawerItem(Icons.Filled.Person, "Profile") { navController.navigate("profile"); onClose() }
        DrawerItem(Icons.Filled.Home, "Home") { navController.navigate("home"); onClose() }
        DrawerItem(Icons.Filled.Settings, "Settings") { navController.navigate("settings"); onClose() }
        DrawerItem(Icons.Filled.Star, "Saved Items") { navController.navigate("saved"); onClose() }
        DrawerItem(Icons.Filled.Edit, "Edit Profile") { navController.navigate("editProfile"); onClose() }
        DrawerItem(Icons.AutoMirrored.Filled.ArrowBack, "Log Out") { logoutUser (navController); onClose() }
    }
}

fun logoutUser(navController: NavHostController) {
    val auth = FirebaseAuth.getInstance()
    auth.signOut()
    Handler(Looper.getMainLooper()).post {
        navController.navigate("log_in") {
            popUpTo("home") { inclusive = true }
        }
    }
}


@Composable
fun DrawerItem(icon: ImageVector, label: String, onClick: () -> Unit) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onClick() }
                .padding(vertical = 16.dp, horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = label,
                tint = TextAndIconColor,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = label,
                fontSize = 16.sp,
                color = TextAndIconColor
            )
        }
        HorizontalDivider(color = TextAndIconColor.copy(alpha = 0.2f))
    }
}

data class Post(val username: String, val image: Int, val image2: Int, val description: String)

val fakePosts = listOf(
    Post("Mert", R.drawable.kombin_1, R.drawable.kombin_2, "Bugünkü kombinim!"),
    Post("Sena", R.drawable.kombin_1, R.drawable.kombin_2, "Sade ve şık."),
    Post("Ahmet", R.drawable.kombin_1, R.drawable.kombin_2, "Bu ceketi çok sevdim!"),
)
