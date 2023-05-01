package com.loperilla.datasource.network

/*****
 * Project: ComposeAnime
 * From: com.loperilla.datasource.network
 * Created By Manuel Lopera on 28/4/23 at 10:57
 * All rights reserved 2023
 */
object KtorConstants {
    const val BASE_URL = "https://animechan.vercel.app/api"

    object QUOTES {
        const val RANDOM = "$BASE_URL/quotes"
        const val BY_ANIME_TITLE = "$RANDOM/anime"
        const val BY_CHARACTER = "$RANDOM/character"
    }

    object AVAILABLE {
        const val BASE = "$BASE_URL/available"
        const val ANIME = "$BASE/anime"
        const val CHARACTER = "$BASE/character"
    }
}
