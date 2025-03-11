package com.example.combiner.ui.screens

import Frame
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.combiner.R
import com.example.combiner.ui.components.PostItem
import com.example.combiner.ui.theme.BackgroundColor
import com.example.combiner.ui.theme.SecondaryTextAndIconColor
import com.example.combiner.ui.theme.TextAndIconColor
import com.example.combiner.ui.theme.PostBackgroundColor

@Composable
fun ProfileScreen() {
    var isGridView by remember { mutableStateOf(false) }
    Scaffold(
        topBar = { ProfileTopBar() }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(BackgroundColor)
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
            containerColor = BackgroundColor,
            titleContentColor = TextAndIconColor
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
                .background(if (!isGridView) SecondaryTextAndIconColor else Color.Transparent)
                .clickable { onToggleChange(false) }
                .padding(12.dp)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.List,
                contentDescription = "List View",
                tint = Color.Black,
                modifier = Modifier.align(Alignment.Center)
            )
        }

        Box(
            modifier = Modifier
                .weight(1f)
                .padding(4.dp)
                .background(if (isGridView) SecondaryTextAndIconColor else Color.Transparent)
                .clickable { onToggleChange(true) }
                .padding(12.dp)
        ) {
            Icon(
                imageVector = Frame,
                contentDescription = "Grid View",
                tint = Color.Black,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}

@Composable
fun UserProfile() {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Column (verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            ){
            Image(
                painter = painterResource(id = R.drawable.man_2),
                contentDescription = "User Profile",
                modifier = Modifier
                    .size(150.dp)
                    .clip(CircleShape)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Column {
            addInfo("Followers",1200)
        }
        addInfo("Followings",1500)
        addInfo("Posts",150)
        Spacer(modifier = Modifier.height(16.dp))
    }
}
@Composable
fun addInfo(text:String,number:Int){
    Column(verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(5.dp)
        ) {
        Text(number.toString())
        Text(text)
    }
}

@Composable
fun UserPosts(isGridView: Boolean) {
    val imageList = listOf(
        R.drawable.kombin_1,
        R.drawable.kombin_2,
        R.drawable.kombin_1,
        R.drawable.kombin_2,
        R.drawable.kombin_1,
        R.drawable.kombin_2 ,
        R.drawable.kombin_1,
        R.drawable.kombin_2,
        R.drawable.kombin_1,
        R.drawable.kombin_2,
        R.drawable.kombin_1,
        R.drawable.kombin_2
    )

    if (isGridView) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier
                .fillMaxSize()
                .background(BackgroundColor),
            contentPadding = PaddingValues(8.dp)
        ) {
            items(imageList) { imageRes ->
                GridPostItem(imageRes)
            }
        }
    } else {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(BackgroundColor)
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
fun GridPostItem(imageRes: Int) {
    Box(
        modifier = Modifier
            .padding(4.dp)
            .size(120.dp)
            .clip(MaterialTheme.shapes.medium)
            .background(PostBackgroundColor),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = "Post Image",
            modifier = Modifier.fillMaxSize(),
        )
    }
}

