package com.loperilla.datasource.di

import android.content.Context
import android.util.Log
import com.loperilla.datasource.network.KtorConstants.BASE_URL
import com.loperilla.datasource.network.api.AnimeApi
import com.loperilla.datasource.network.api.CharactersApi
import com.loperilla.datasource.network.api.QuoteApi
import com.loperilla.datasource.network.impl.AnimeImpl
import com.loperilla.datasource.network.impl.CharactersImpl
import com.loperilla.datasource.network.impl.QuoteImpl
import com.loperilla.datasource.network.interceptor.NetworkInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.observer.ResponseObserver
import io.ktor.client.request.header
import io.ktor.client.statement.request
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import javax.inject.Singleton


/*****
 * Project: CompraCasa
 * From: com.loperilla.datasource.di
 * Created By Manuel Lopera on 23/4/23 at 14:03
 * All rights reserved 2023
 */

@Module
@InstallIn(SingletonComponent::class)
object DataSourceDependencyInjector {
    @Provides
    @Singleton
    fun provideHttpClient(networkInterceptor: NetworkInterceptor, json: Json): HttpClient {
        return HttpClient(OkHttp) {
            install(Logging) {
                logger = object : Logger {
                    override fun log(message: String) {
                        Log.v("Logger Ktor =>", message)
                    }
                }
                level = LogLevel.INFO
            }

            install(ResponseObserver) {
                onResponse { response ->
                    Log.d("HTTP status:", "${response.status.value}")
                    Log.d("HTTP requestTime:", "${response.requestTime}")
                    Log.d("HTTP responseTime:", "${response.responseTime}")
                    Log.d("HTTP url:", "${response.request.url}")
                    Log.d("HTTP value:", "${response.status.value}")
                }
            }

            install(DefaultRequest) {
                header(HttpHeaders.ContentType, ContentType.Application.Json)
                url {
                    url(BASE_URL)
//                    parameters.append("api_key", "59cd6896d8432f9c69aed9b86b9c2931")
                }
            }

            install(HttpTimeout) {
                requestTimeoutMillis = 10000
            }

            install(ContentNegotiation) {
                json(
                    json
                )
                engine {
                    addInterceptor { chain: Interceptor.Chain ->
                        chain.request().let {
                            chain.proceed(it)
                        }
                    }

                    addNetworkInterceptor(networkInterceptor)
                }
            }
        }
    }

    @Singleton
    @Provides
    fun provideQuoteApi(
        httpClient: HttpClient,
        json: Json
    ): QuoteApi = QuoteImpl(httpClient, json)

    @OptIn(ExperimentalSerializationApi::class)
    @Singleton
    @Provides
    fun providesJson() = Json {
        Json {
            prettyPrint = true
            isLenient = true
            ignoreUnknownKeys = true
            explicitNulls = false
        }
    }

    @Singleton
    @Provides
    fun provideAnimeApi(
        httpClient: HttpClient,
        json: Json
    ): AnimeApi = AnimeImpl(httpClient, json)

    @Singleton
    @Provides
    fun provideNetworkInterceptor(context: Context) = NetworkInterceptor(context)

    @Singleton
    @Provides
    fun provideCharacterApi(
        httpClient: HttpClient,
        json: Json
    ): CharactersApi = CharactersImpl(httpClient, json)


    @Provides
    @Singleton
    fun provideApplicationContext(
        @ApplicationContext context: Context
    ): Context = context
}
