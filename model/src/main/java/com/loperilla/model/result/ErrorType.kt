package com.loperilla.model.result

/*****
 * Project: ComposeAnime
 * From: com.loperilla.datasource.network.model
 * Created By Manuel Lopera on 6/5/23 at 12:24
 * All rights reserved 2023
 */

sealed class ErrorType(val errorCode: Int) {
    object BadRequest : ErrorType(400)
    object InvalidData : ErrorType(401)
    object Forbidden : ErrorType(403)
    object InternalServerError : ErrorType(500)
    data class UncontrolledError(val code: Int) : ErrorType(code)
    data class ExceptionError(val exception: Exception) : ErrorType(666)
}
