package com.example.combiner.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.example.combiner.R

@Composable
fun AiAssistantScreen() {
    val hats = listOf(R.drawable.hat1, R.drawable.hat2, R.drawable.hat3)
    val shirts = listOf(R.drawable.shirt1, R.drawable.shirt2, R.drawable.shirt3)
    val pants = listOf(R.drawable.pants1, R.drawable.pants2, R.drawable.pants3)
    val shoes = listOf(R.drawable.shoes1, R.drawable.shoes2, R.drawable.shoes3)

    var selectedHat by remember { mutableStateOf<Int?>(null) }
    var selectedShirt by remember { mutableStateOf<Int?>(null) }
    var selectedPants by remember { mutableStateOf<Int?>(null) }
    var selectedShoes by remember { mutableStateOf<Int?>(null) }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(20.dp))

        SelectionRow(
            onPrev = { selectedHat = (selectedHat?.minus(1)?.takeIf { it >= 0 } ?: hats.lastIndex) },
            onNext = { selectedHat = (selectedHat?.plus(1)?.takeIf { it <= hats.lastIndex } ?: 0) }
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.human_icon),
                contentDescription = "Human Icon",
                modifier = Modifier.size(250.dp)
            )

            // Seçili Şapka (Başa hizalandı)
            selectedHat?.let {
                Image(
                    painter = painterResource(id = hats[it]),
                    contentDescription = "Selected Hat",
                    modifier = Modifier
                        .size(80.dp)
                        .align(Alignment.TopCenter)
                        .offset(y = (-90).dp)
                )
            }

            selectedShirt?.let {
                Image(
                    painter = painterResource(id = shirts[it]),
                    contentDescription = "Selected Shirt",
                    modifier = Modifier
                        .size(width = 150.dp, height = 120.dp)
                        .align(Alignment.Center)
                        .offset(y = 10.dp)
                )
            }

            selectedPants?.let {
                Image(
                    painter = painterResource(id = pants[it]),
                    contentDescription = "Selected Pants",
                    modifier = Modifier
                        .size(width = 140.dp, height = 120.dp)
                        .align(Alignment.BottomCenter)
                        .offset(y = (-40).dp)
                )
            }

            selectedShoes?.let {
                Image(
                    painter = painterResource(id = shoes[it]),
                    contentDescription = "Selected Shoes",
                    modifier = Modifier
                        .size(100.dp)
                        .align(Alignment.BottomCenter)
                        .offset(y = 20.dp)
                )
            }
        }

        SelectionRow(
            onPrev = { selectedShirt = (selectedShirt?.minus(1)?.takeIf { it >= 0 } ?: shirts.lastIndex) },
            onNext = { selectedShirt = (selectedShirt?.plus(1)?.takeIf { it <= shirts.lastIndex } ?: 0) }
        )

        SelectionRow(
            onPrev = { selectedPants = (selectedPants?.minus(1)?.takeIf { it >= 0 } ?: pants.lastIndex) },
            onNext = { selectedPants = (selectedPants?.plus(1)?.takeIf { it <= pants.lastIndex } ?: 0) }
        )

        SelectionRow(
            onPrev = { selectedShoes = (selectedShoes?.minus(1)?.takeIf { it >= 0 } ?: shoes.lastIndex) },
            onNext = { selectedShoes = (selectedShoes?.plus(1)?.takeIf { it <= shoes.lastIndex } ?: 0) }
        )
    }
}

@Composable
fun SelectionRow(onPrev: () -> Unit, onNext: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(onClick = onPrev) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Previous",
                tint = Color.Black
            )
        }
        IconButton(onClick = onNext) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                contentDescription = "Next",
                tint = Color.Black
            )
        }
    }
}
