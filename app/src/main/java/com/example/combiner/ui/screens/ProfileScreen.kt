package com.example.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
import com.example.combiner.ui.components.PostItem
import com.example.combiner.ui.screens.fakePosts
import com.example.combiner.ui.theme.BeigeCream
import com.example.combiner.ui.theme.BeigeMocha
import com.example.combiner.ui.theme.Caramel
import com.example.combiner.ui.theme.DeepChocolate
import com.example.combiner.ui.theme.GoldenSand
import com.example.combiner.ui.theme.SoftIvory

@Composable
fun ProfileScreen(navController: NavHostController) {
    var isGridView by remember { mutableStateOf(false) }
    Scaffold(
        topBar = { ProfileTopBar() }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(BeigeCream)
        ) {
            UserProfile()
            ToggleViewSelector(isGridView) { isGridView = it }
            UserPosts(isGridView)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileTopBar() {
    TopAppBar(title = { Text("Profile") },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = BeigeCream,
            titleContentColor = DeepChocolate
        ))
}

@Composable
fun ToggleViewSelector(isGridView: Boolean, onToggleChange: (Boolean) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .padding(4.dp)
                .background(if (!isGridView) Caramel else Color.Transparent)
                .clickable { onToggleChange(false) }
                .padding(12.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.List,
                contentDescription = "List View",
                tint = Color.Black,
                modifier = Modifier.align(Alignment.Center)
            )
        }

        Box(
            modifier = Modifier
                .weight(1f)
                .padding(4.dp)
                .background(if (isGridView) Caramel else Color.Transparent)
                .clickable { onToggleChange(true) }
                .padding(12.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.AccountBox,
                contentDescription = "Grid View",
                tint = Color.Black,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}

@Composable
fun UserProfile() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.man_2),
            contentDescription = "User Profile",
            modifier = Modifier
                .size(100.dp)
                .padding(4.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.height(8.dp))

        Text("Username", style = MaterialTheme.typography.titleMedium)
        Text("Followers: 1200", style = MaterialTheme.typography.bodyMedium)
        Text("Following: 180", style = MaterialTheme.typography.bodyMedium)
        Text("Posts: 150", style = MaterialTheme.typography.bodyMedium)
        Spacer(modifier = Modifier.height(16.dp))
    }
}


@Composable
fun UserPosts(isGridView: Boolean) {
    if (isGridView) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(4),
            modifier = Modifier.fillMaxSize().background(BeigeCream),
            contentPadding = PaddingValues(8.dp)
        ) {
            items(20) { index ->
                GridPostItem(index)
            }
        }
    } else {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(BeigeCream)
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

@Composable
fun GridPostItem(index: Int) {
    Box(
        modifier = Modifier
            .padding(4.dp)
            .size(80.dp)
            .background(DeepChocolate)
    ) {
        Text(
            text = "Post $index",
            color = GoldenSand,
            modifier = Modifier.align(Alignment.Center),
            style = MaterialTheme.typography.bodySmall
        )
    }
}
