package com.example.combiner.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.combiner.R
import com.example.combiner.ui.theme.*

@Composable
fun ExploreScreen() {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor)
    ){

        Box(modifier = Modifier.fillMaxWidth()
            .padding(8.dp), contentAlignment = Alignment.Center
        ){
            SearchBar()
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            items(10) { index ->
                ExploreRow(index)
            }
        }
    }
}

@Composable
fun SearchBar() {
    var searchText by remember { mutableStateOf("") }

    TextField(
        value = searchText,
        onValueChange = { searchText = it },
        placeholder = { Text("Search...") },
        leadingIcon = {
            Icon(imageVector = Icons.Default.Search, contentDescription = "Search Icon")
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp,16.dp,0.dp,0.dp)
            .clip(RoundedCornerShape(20.dp)),
        singleLine = true,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = SecondaryTextAndIconColor,
            unfocusedContainerColor = PostBackgroundColor,
            disabledContainerColor = Color.White,
            cursorColor = Color.Black,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
    )
}

@Composable
fun ExploreRow(index: Int) {
    val isBigImageOnRight = index % 2 == 0

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        if (isBigImageOnRight) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                SmallImage()
                SmallImage()
            }
            BigImage(modifier = Modifier.weight(1f))
        } else {
            BigImage(modifier = Modifier.weight(1f))
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                SmallImage()
                SmallImage()
            }
        }
    }
}

@Composable
fun SmallImage(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = R.drawable.kombin_1),
        contentDescription = "Small Image",
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .aspectRatio(3f / 2f)
    )
}

@Composable
fun BigImage(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = R.drawable.kombin_2),
        contentDescription = "Big Image",
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .aspectRatio(3.5f / 4f)
    )
}
