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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import com.loperilla.core_ui.LOW

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
    isInputFocusedListener: (Boolean) -> Unit,
    placeHolderText: String
) {
    var isInputFocused by rememberSaveable { mutableStateOf(false) }
    val colors = TextFieldDefaults.colors(
        focusedContainerColor = Color(0xFFDEDDDD),
        unfocusedContainerColor = Color(0xFFDEDDDD),
        disabledContainerColor = Color(0xFFDEDDDD),
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
        textStyle = TextStyle(
            color = Color(0xFF636262)
        ),
        modifier = modifier
            .fillMaxWidth()
            .padding(LOW)
            .onFocusChanged {
                isInputFocused = it.isFocused
                isInputFocusedListener(it.isFocused)
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
