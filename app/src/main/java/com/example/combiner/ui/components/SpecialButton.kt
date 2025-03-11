package com.example.combiner.ui.components


import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.combiner.ui.theme.TextAndIconColor

@Composable
fun SpecialButton(text:String, onClickFun: ()->Unit){
    Button(onClickFun,
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier.padding(10.dp)
        ){
        Text(text, color = TextAndIconColor)
    }
}
@Preview(showBackground = true)
@Composable
fun PreviewButton(){
     SpecialButton("Log In",{
     })
}