package com.loperilla.datasource.network.api

/*****
 * Project: ComposeAnime
 * From: com.loperilla.datasource.network.api
 * Created By Manuel Lopera on 29/4/23 at 16:10
 * All rights reserved 2023
 */
interface AnimeApi {
    suspend fun getAllAnime(): Result<List<String>>
}
