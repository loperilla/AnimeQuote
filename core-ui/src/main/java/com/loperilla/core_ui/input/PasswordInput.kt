package com.loperilla.core_ui.input

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.loperilla.core_ui.LOW

/*****
 * Project: CompraCasa
 * From: com.loperilla.core_ui.input
 * Created By Manuel Lopera on 22/4/23 at 20:49
 * All rights reserved 2023
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordInput(
    modifier: Modifier,
    inputValue: String,
    placeholderValue: String = "*********",
    onValueChange: (String) -> Unit
) {
    var isPasswordVisible by rememberSaveable { mutableStateOf(false) }
    val colors = TextFieldDefaults.textFieldColors(
        textColor = Color(0xFF636262),
        containerColor = Color(0xFFDEDDDD),
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent
    )
    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = LOW),
        value = inputValue,
        maxLines = 1,
        singleLine = true,
        colors = colors,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        placeholder = {
            Text(text = placeholderValue)
        },
        onValueChange = {
            onValueChange(it)
        },
        leadingIcon = {
            Icon(imageVector = Icons.Filled.Lock, contentDescription = null)
        },
        trailingIcon = {
            val endIcon = if (isPasswordVisible) {
                Icons.Filled.VisibilityOff
            } else {
                Icons.Filled.Visibility
            }

            IconButton(
                onClick = { isPasswordVisible = !isPasswordVisible }
            ) {
                Icon(imageVector = endIcon, contentDescription = "show or hide password")
            }
        },
        visualTransformation = if (isPasswordVisible) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        }
    )
}
