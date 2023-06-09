package com.loperilla.core_ui.previews

import android.content.res.Configuration
import android.os.Build
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview

/*****
 * Project: CompraCasa
 * From: com.loperilla.core_ui
 * Created By Manuel Lopera on 21/4/23 at 16:10
 * All rights reserved 2023
 */

@Preview(
    name = "PIXEL_XL API 33",
    apiLevel = Build.VERSION_CODES.TIRAMISU,
    locale = "es",
    showSystemUi = true,
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    device = Devices.PIXEL_4_XL
)

annotation class PIXEL_33_NIGHT