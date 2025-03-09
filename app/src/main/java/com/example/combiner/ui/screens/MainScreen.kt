package com.example.combiner.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ui.screens.ProfileScreen
import com.example.combiner.ui.theme.*

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { BottomNavigationBar(navController) }
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(padding)
        ) {
            composable("home") { HomeScreen(navController) }
            composable("profile") { ProfileScreen(navController) }
            composable("explore") { ExploreScreen(navController) }
            composable("ai_assistant") { AiAssistantScreen() }
            composable("log_in") { LogInScreen(navController) }
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(
        BottomNavItem("home", "Home", Icons.Default.Home),
        BottomNavItem("explore", "Keşfet", Icons.Default.MoreVert),
        BottomNavItem("ai_assistant", "AI Asistan", Icons.Default.Face),
        BottomNavItem("profile", "Profile", Icons.Default.Person)
    )

    NavigationBar(containerColor = BeigeCream) { // Kahverengi arka plan
        val currentRoute = navController.currentDestination?.route
        items.forEach { item ->
            NavigationBarItem(
                label = { Text(item.label, color = DeepChocolate) }, // Yazılar beyaz
                icon = { Icon(item.icon, contentDescription = item.label, tint = DeepChocolate) }, // Altın ikonlar
                selected = currentRoute == item.route,
                onClick = { navController.navigate(item.route) }
            )
        }
    }
}


data class BottomNavItem(val route: String, val label: String, val icon: androidx.compose.ui.graphics.vector.ImageVector)
