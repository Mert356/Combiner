package com.example.combiner.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.combiner.R
import com.example.combiner.ui.theme.*

@Composable
fun ExploreScreen(navController: NavHostController) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(BeigeCream) // Yumuşak bej arka plan
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        items(10) { index ->
            ExploreRow(index)
        }
    }
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
            .clip(RoundedCornerShape(10.dp)) // Kenarları yuvarladık
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
            .clip(RoundedCornerShape(10.dp)) // Kenarları yuvarladık
            .aspectRatio(3.5f / 4f)
    )
}
