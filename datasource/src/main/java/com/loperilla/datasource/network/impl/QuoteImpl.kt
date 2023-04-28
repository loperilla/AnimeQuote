package com.loperilla.datasource.network.impl

import com.loperilla.datasource.model.QuoteNetwork
import com.loperilla.datasource.network.KtorConstants.QUOTES
import com.loperilla.datasource.network.api.QuoteApi
import io.ktor.client.HttpClient
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.request.get
import io.ktor.client.request.url
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
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
): QuoteApi {
    override suspend fun getRandomQuotes(): Result<List<QuoteNetwork>> {
        return runCatching {
            val httpResponse: HttpResponse = ktorClient.get {
                url(QUOTES.RANDOM)
            }
            json.decodeFromString(
                ListSerializer(QuoteNetwork.serializer()),
                httpResponse.bodyAsText()
            )
        }
//        catch(e: RedirectResponseException) {
//            // 3xx - responses
//            println("Error: ${e.response.status.description}")
//            Response.Exception(e.response.status.description)
//        } catch(e: ClientRequestException) {
//            // 4xx - responses
//            println("Error: ${e.response.status.description}")
//            Response.Exception(e.response.status.description)
//        } catch(e: ServerResponseException) {
//            // 5xx - responses
//            println("Error: ${e.response.status.description}")
//            Response.Exception(e.response.status.description)
//        } catch(e: Exception) {
//            println("Error: ${e.message}")
//            Response.Exception(e.message?: "Excepción genérica")
//        }
    }
}
