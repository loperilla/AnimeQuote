package com.loperilla.datasource.model

import com.loperilla.model.quote.Quote
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/*****
 * Project: ComposeAnime
 * From: com.loperilla.datasource.model
 * Created By Manuel Lopera on 28/4/23 at 10:58
 * All rights reserved 2023
 */
@Serializable
data class QuoteNetwork(
    @SerialName("anime")
    val anime: String,
    @SerialName("character")
    val character: String,
    @SerialName("quote")
    val quote: String
)

fun QuoteNetwork.toDomain(): Quote {
    return Quote(
        anime, character, quote
    )
}