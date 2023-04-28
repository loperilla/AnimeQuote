package com.loperilla.datasource.network

/*****
 * Project: ComposeAnime
 * From: com.loperilla.model.response
 * Created By Manuel Lopera on 28/4/23 at 11:18
 * All rights reserved 2023
 */
sealed class Response<T> {
    data class Success<T>(val data: T?): Response<T>()
    data class Exception<T>(val errorMsg: String): Response<T>()
}
