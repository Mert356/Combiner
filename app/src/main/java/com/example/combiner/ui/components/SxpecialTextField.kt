package com.example.combiner.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.combiner.ui.theme.BackgroundColor
import com.example.combiner.ui.theme.SecondaryTextAndIconColor
import com.example.combiner.ui.theme.TextAndIconColor

@Composable
fun SpecialTextField(
    text: String,
    onClickFun: (String) -> Unit,
    hint: String = "Enter text",
    isPassword: Boolean = false,
) {
    val visualTransformation = if (isPassword) PasswordVisualTransformation() else null
    TextField(
        value = text,
        onValueChange = onClickFun,
        placeholder = { Text(hint, color = TextAndIconColor) },
        shape = RoundedCornerShape(20.dp),
        colors = TextFieldDefaults.colors(
            cursorColor = Color.Black,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedContainerColor = SecondaryTextAndIconColor,
            unfocusedContainerColor = BackgroundColor
        ),
        singleLine = true,
        modifier = Modifier
            .clip(RoundedCornerShape(25.dp))
            .background(TextAndIconColor)
            .padding(5.dp),
        visualTransformation = visualTransformation ?: VisualTransformation.None,
    )
    Spacer(modifier = Modifier.padding(10.dp))
}

@Preview(showBackground = true)
@Composable
fun PreviewTextField() {
    SpecialTextField("Mert", onClickFun = {})
    SpecialTextField("", onClickFun = {})
}

