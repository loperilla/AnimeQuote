package com.loperilla.datasource.firebase.auth

import com.google.firebase.auth.FirebaseUser
import com.loperilla.model.auth.AuthResult

interface IFirebaseAuthDataSource {
    suspend fun doFirebaseLogin(email: String, password: String): AuthResult

    fun getUserAuth(): FirebaseUser?
}