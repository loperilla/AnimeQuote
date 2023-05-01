package com.loperilla.core_ui.input

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import com.loperilla.core_ui.LOW
import com.loperilla.core_ui.previews.PIXEL_33_NIGHT

/*****
 * Project: ComposeAnime
 * From: com.loperilla.onboarding.common
 * Created By Manuel Lopera on 1/5/23 at 10:13
 * All rights reserved 2023
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchField(
    modifier: Modifier = Modifier,
    textValue: String,
    onTextChange: (String) -> Unit,
    placeHolderText: String
) {
    var isInputFocused by rememberSaveable { mutableStateOf(false) }
    val colors = TextFieldDefaults.textFieldColors(
        textColor = Color(0xFF636262),
        containerColor = Color(0xFFF5F5F5),
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent
    )
    OutlinedTextField(
        value = textValue,
        maxLines = 1,
        colors = colors,
        onValueChange = {
            onTextChange(it)
        },
        modifier = modifier
            .fillMaxWidth()
            .padding(LOW)
            .onFocusChanged {
                isInputFocused = it.isFocused
            },
        placeholder = {
            Text(text = placeHolderText)
        },
        trailingIcon = {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = "input search icon",
                tint = if (isInputFocused) {
                    Color.Blue
                } else {
                    Color.Green
                }
            )
        }
    )
}

@PIXEL_33_NIGHT
@Composable
fun SearchFieldPreview() {
    SearchField(
        textValue = "",
        placeHolderText = "hola",
        onTextChange = {

        }
    )
}