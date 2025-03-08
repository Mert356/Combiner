package com.example.combiner.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
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
import kotlinx.coroutines.launch
@Composable
fun HomeScreen(navController: NavHostController) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        scrimColor = Color.Transparent,
        drawerContent = {
            ModalDrawerSheet(
                modifier = Modifier.width(280.dp)
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
            }
        ) { padding ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFF5F5F5))
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
                Icon(Icons.Filled.Menu, contentDescription = "Menu")
            }
        }
    )
}

@Composable
fun DrawerContent(navController: NavHostController, onClose: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .width(280.dp)
            .background(MaterialTheme.colorScheme.surface)
            .padding(16.dp),
        horizontalAlignment = Alignment.Start
    ) {

        Text(
            text = "My Profile",
            style = MaterialTheme.typography.titleLarge.copy(fontSize = 20.sp),
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(bottom = 16.dp)
        )


        DrawerItem(
            icon = Icons.Filled.Person,
            label = "Profile",
            onClick = { navController.navigate("profile"); onClose() }
        )
        DrawerItem(
            icon = Icons.Filled.Home,
            label = "Home",
            onClick = { navController.navigate("home"); onClose() }
        )
        DrawerItem(
            icon = Icons.Filled.Settings,
            label = "Settings",
            onClick = { navController.navigate("settings"); onClose() }
        )
        DrawerItem(
            icon = Icons.Filled.Star,
            label = "Saved Items",
            onClick = { navController.navigate("saved"); onClose() }
        )
        DrawerItem(
            icon = Icons.Filled.Edit,
            label = "Edit Profile",
            onClick = { navController.navigate("editProfile"); onClose() }
        )
    }
}

@Composable
fun DrawerItem(icon: androidx.compose.ui.graphics.vector.ImageVector, label: String, onClick: () -> Unit) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onClick() }
                .padding(vertical = 12.dp, horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = label,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = label,
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
        Divider(color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f))
    }
}

data class Post(val username: String, val image: Int, val image2: Int, val description: String)

val fakePosts = listOf(
    Post("Mert", R.drawable.kombin_1,R.drawable.kombin_2,"Bugünkü kombinim!"),
    Post("Sena", R.drawable.kombin_1,R.drawable.kombin_2,"Sade ve şık."),
    Post("Ahmet", R.drawable.kombin_1,R.drawable.kombin_2,"Bu ceketi çok sevdim!"),
)
