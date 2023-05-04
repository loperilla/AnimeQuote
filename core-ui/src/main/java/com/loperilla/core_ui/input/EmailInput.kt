package com.loperilla.core_ui.input

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import com.loperilla.core_ui.LOW

/*****
 * Project: CompraCasa
 * From: com.loperilla.core_ui.input
 * Created By Manuel Lopera on 22/4/23 at 20:33
 * All rights reserved 2023
 */

@Composable
fun EmailInput(
    inputValue: String,
    modifier: Modifier = Modifier,
    placeholderValue: String = "correo@dominio.com",
    onValueChange: (String) -> Unit
) {
    val isFocused by rememberSaveable { mutableStateOf(false) }
    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = LOW),
        value = inputValue,
        maxLines = 1,
        singleLine = true,
        leadingIcon = {
            Icon(imageVector = Icons.Filled.Person, contentDescription = "emailIcon")
        },
        placeholder = {
            Text(text = placeholderValue)
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        onValueChange = {
            onValueChange(it)
        },
        textStyle = TextStyle(
            color = Color(0xFF636262)
        ),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color(0xFFDEDDDD),
            unfocusedContainerColor = Color(0xFFDEDDDD),
            disabledContainerColor = Color(0xFFDEDDDD),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        )
    )
}
