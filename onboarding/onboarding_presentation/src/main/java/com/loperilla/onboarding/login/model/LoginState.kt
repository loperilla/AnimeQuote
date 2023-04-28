package com.loperilla.onboarding.login.model

/*****
 * Project: CompraCasa
 * From: com.loperilla.onboarding.login
 * Created By Manuel Lopera on 22/4/23 at 19:10
 * All rights reserved 2023
 */
data class LoginState(
    val emailValue: String = "",
    val passwordValue: String = "",
    val bothValueValid: Boolean = false,
    val requestLaunched: Boolean = false,
    val requestFail: Boolean = false
)
