package com.example.combiner.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.combiner.ui.theme.DeepChocolate

@Composable
fun SpecialButton(text:String, onClickFun: ()->Unit){
    Button(onClickFun,
        shape = RoundedCornerShape(20.dp),
        ){
        Text(text, color = DeepChocolate)
    }
}
@Preview(showBackground = true)
@Composable
fun PreviewButton(){
     SpecialButton("Log In",{
     })
}