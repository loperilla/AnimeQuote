package com.loperilla.datasource.network.impl

import com.loperilla.datasource.network.KtorConstants.AVAILABLE
import com.loperilla.datasource.network.api.CharactersApi
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.url
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.json.Json
import javax.inject.Inject

/*****
 * Project: ComposeAnime
 * From: com.loperilla.datasource.network.impl
 * Created By Manuel Lopera on 1/5/23 at 18:28
 * All rights reserved 2023
 */
class CharactersImpl @Inject constructor(
    private val ktorClient: HttpClient,
    private val json: Json
) : CharactersApi {
    override suspend fun getAllCharacters(): Result<List<String>> = runCatching {
        val httpResponse: HttpResponse = ktorClient.get {
            url(AVAILABLE.CHARACTER)
        }

        json.decodeFromString(
            ListSerializer(String.serializer()),
            httpResponse.bodyAsText()
        )
    }
}