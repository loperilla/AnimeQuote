package com.loperilla.onboarding.utils

import android.util.Patterns

/*****
 * Project: CompraCasa
 * From: com.loperilla.onboarding.utils
 * Created By Manuel Lopera on 22/4/23 at 19:55
 * All rights reserved 2023
 */
object InputValidators {
    fun isEmailValid(email: String) = Patterns.EMAIL_ADDRESS.matcher(email).matches()

    fun isPasswordValid(password: String) = password.length > 5
}
