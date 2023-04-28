package com.loperilla.model.result

/*****
 * Project: ComposeAnime
 * From: com.loperilla.model.result
 * Created By Manuel Lopera on 28/4/23 at 11:39
 * All rights reserved 2023
 */
sealed class CallResult<T> {
    data class Success<T>(val data: T?): CallResult<T>()
    data class Exception<T>(val errorMsg: String): CallResult<T>()
}
