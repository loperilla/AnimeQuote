package com.loperilla.datasource.model

import com.loperilla.datasource.database.entity.QuoteEntity
import com.loperilla.model.interfaces.IRemoteResponse
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
) : IRemoteResponse<QuoteEntity, Quote>() {
    override fun toEntity(): QuoteEntity =
        QuoteEntity(
            anime = this.anime,
            quote = this.quote,
            character = this.character
        )

    override fun toDomain(): Quote = Quote(
        anime = this.anime,
        character = this.character,
        quote = quote
    )
}
