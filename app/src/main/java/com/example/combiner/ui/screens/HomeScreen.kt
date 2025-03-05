package com.example.combiner.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.combiner.R
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
                    .padding(padding),
                contentPadding = PaddingValues(16.dp)
            ) {
                items(fakePosts) { post ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        shape = MaterialTheme.shapes.medium,
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Row {
                                Image(
                                    painter = painterResource(id = R.drawable.kombin_1),
                                    contentDescription = "Profile Picture",
                                    modifier = Modifier
                                        .size(40.dp)
                                        .clip(MaterialTheme.shapes.medium)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(post.username, fontWeight = FontWeight.Bold)
                            }
                            Spacer(modifier = Modifier.height(8.dp))
                            Image(
                                painter = painterResource(id = R.drawable.kombin_2),
                                contentDescription = "Outfit Post",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(250.dp)
                                    .clip(MaterialTheme.shapes.medium)
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(post.description, fontSize = 14.sp)

                            // Beğenme, Yorum, Kaydetme Butonları
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 8.dp),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Row {
                                    IconButton(onClick = { }) {
                                        Icon(
                                            imageVector = Icons.Filled.Favorite,
                                            contentDescription = "Like",
                                        )
                                    }
                                    IconButton(onClick = { /* Yorum Yap */ }) {
                                        Icon(Icons.Filled.Phone, contentDescription = "Comment")
                                    }
                                }

                                IconButton(onClick = { /* Kaydetme İşlemi */ }) {
                                    Icon(Icons.Filled.Star, contentDescription = "Save")
                                }
                            }
                        }
                    }
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
            .background(MaterialTheme.colorScheme.surface) // Arka plan rengi
            .padding(16.dp),
        horizontalAlignment = Alignment.Start
    ) {
        // Profil Bölümü
        Text(
            text = "My Profile",
            style = MaterialTheme.typography.titleLarge.copy(fontSize = 20.sp),
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Menü Seçenekleri
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

// ✅ Tek tek Drawer seçeneklerini tasarlayan fonksiyon
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
                tint = MaterialTheme.colorScheme.primary, // Modern renk
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = label,
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
        Divider(color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f)) // Ayrım çizgisi
    }
}



data class Post(val username: String, val description: String)

val fakePosts = listOf(
    Post("Ahmet", "Bugünkü kombinim!"),
    Post("Elif", "Sade ve şık."),
    Post("Mert", "Bu ceketi çok sevdim!"),
)
