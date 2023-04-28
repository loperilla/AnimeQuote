package com.loperilla.model.auth

/*****
 * Project: CompraCasa
 * From: com.loperilla.data.firebase.auth
 * Created By Manuel Lopera on 21/4/23 at 16:59
 * All rights reserved 2023
 */
sealed class AuthResult {
    data class AuthSuccess(val uidResult: String) : AuthResult()
    data class AuthFail(val errorMessage: String) : AuthResult()
    object AuthNone : AuthResult()
    object LoadingRequest : AuthResult()
}
