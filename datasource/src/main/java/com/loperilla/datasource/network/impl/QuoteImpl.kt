package com.loperilla.datasource.network.impl

import com.loperilla.datasource.model.QuoteNetwork
import com.loperilla.datasource.network.KtorConstants.QUOTES
import com.loperilla.datasource.network.api.QuoteApi
import io.ktor.client.HttpClient
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.request.accept
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.url
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json
import javax.inject.Inject

/*****
 * Project: ComposeAnime
 * From: com.loperilla.datasource.network.impl
 * Created By Manuel Lopera on 28/4/23 at 11:04
 * All rights reserved 2023
 */
class QuoteImpl @Inject constructor(
    private val ktorClient: HttpClient,
    private val json: Json
) : QuoteApi {
    override suspend fun getRandomQuotes(page: Int): Result<List<QuoteNetwork>> = runCatching {
        try {
            val httpResponse: HttpResponse = ktorClient.get {
                url(QUOTES.RANDOM)
                parameter("pages", "$page")
                accept(ContentType.Application.Json)
            }
            json.decodeFromString(
                ListSerializer(QuoteNetwork.serializer()),
                httpResponse.bodyAsText()
            )
        } catch (e: RedirectResponseException) {
            // 3xx - responses
            println("Error: ${e.response.status.description}")
            Result.failure<List<QuoteNetwork>>(e)
            emptyList()
        } catch (e: ClientRequestException) {
            // 4xx - responses
            println("Error: ${e.response.status.description}")
            Result.failure<List<QuoteNetwork>>(e)
            emptyList()
        } catch (e: ServerResponseException) {
            // 5xx - responses
            println("Error: ${e.response.status.description}")
            Result.failure<List<QuoteNetwork>>(e)
            emptyList()
        } catch (e: Exception) {
            println("Error: ${e.message}")
            Result.failure<List<QuoteNetwork>>(e)
            emptyList()
        }
    }

    override suspend fun getRandomQuotesByAnimeTitle(animeTitle: String): Result<List<QuoteNetwork>> = runCatching {
        val httpResponse: HttpResponse = ktorClient.get {
            url(QUOTES.BY_ANIME_TITLE)
            parameter("title", animeTitle)
        }

        json.decodeFromString(
            ListSerializer(QuoteNetwork.serializer()),
            httpResponse.bodyAsText()
        )
    }

    override suspend fun getRandomQuotesByCharacter(name: String): Result<List<QuoteNetwork>> = runCatching {
        val httpResponse: HttpResponse = ktorClient.get {
            url(QUOTES.BY_CHARACTER)
            parameter("name", name)
        }

        json.decodeFromString(
            ListSerializer(QuoteNetwork.serializer()),
            httpResponse.bodyAsText()
        )
    }
}
