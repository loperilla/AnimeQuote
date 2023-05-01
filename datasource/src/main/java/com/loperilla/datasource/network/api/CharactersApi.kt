package com.loperilla.datasource.network.api

/*****
 * Project: ComposeAnime
 * From: com.loperilla.datasource.network.api
 * Created By Manuel Lopera on 1/5/23 at 18:27
 * All rights reserved 2023
 */
interface CharactersApi {
    suspend fun getAllCharacters(): Result<List<String>>
}
