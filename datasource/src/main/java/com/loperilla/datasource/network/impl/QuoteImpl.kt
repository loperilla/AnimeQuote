package com.loperilla.datasource.network.impl

import com.loperilla.datasource.model.QuoteNetwork
import com.loperilla.datasource.network.KtorConstants.QUOTES
import com.loperilla.datasource.network.api.QuoteApi
import com.loperilla.datasource.network.Response
import io.ktor.client.HttpClient
import io.ktor.client.features.ClientRequestException
import io.ktor.client.features.RedirectResponseException
import io.ktor.client.features.ServerResponseException
import io.ktor.client.request.get
import javax.inject.Inject

/*****
 * Project: ComposeAnime
 * From: com.loperilla.datasource.network.impl
 * Created By Manuel Lopera on 28/4/23 at 11:04
 * All rights reserved 2023
 */
class QuoteImpl @Inject constructor(
    private val ktorClient: HttpClient
): QuoteApi {
    override suspend fun getRandomQuotes(): Response<List<QuoteNetwork>> {
        return try {
            Response.Success(ktorClient.get(QUOTES.RANDOM))
        } catch(e: RedirectResponseException) {
            // 3xx - responses
            println("Error: ${e.response.status.description}")
            Response.Exception(e.response.status.description)
        } catch(e: ClientRequestException) {
            // 4xx - responses
            println("Error: ${e.response.status.description}")
            Response.Exception(e.response.status.description)
        } catch(e: ServerResponseException) {
            // 5xx - responses
            println("Error: ${e.response.status.description}")
            Response.Exception(e.response.status.description)
        } catch(e: Exception) {
            println("Error: ${e.message}")
            Response.Exception(e.message?: "Excepción genérica")
        }
    }
}
