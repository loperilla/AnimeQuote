package com.loperilla.datasource.network.impl

import com.loperilla.datasource.network.KtorConstants.AVAILABLE
import com.loperilla.datasource.network.api.AnimeApi
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
 * Created By Manuel Lopera on 29/4/23 at 16:12
 * All rights reserved 2023
 */
class AnimeImpl @Inject constructor(
    private val ktorClient: HttpClient,
    private val json: Json
) : AnimeApi {
    override suspend fun getAllAnime(): Result<List<String>> = runCatching {
        val httpResponse: HttpResponse = ktorClient.get {
            url(AVAILABLE.ANIME)
        }

        json.decodeFromString(
            ListSerializer(String.serializer()),
            httpResponse.bodyAsText()
        )
    }
}