package com.loperilla.datasource.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.loperilla.model.interfaces.IEntityModel
import com.loperilla.model.quote.Quote

/*****
 * Project: ComposeAnime
 * From: com.loperilla.datasource.database.entity
 * Created By Manuel Lopera on 3/5/23 at 15:50
 * All rights reserved 2023
 */
@Entity
data class QuoteEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,
    val anime: String,
    val character: String,
    val quote: String
) : IEntityModel<Quote>() {
    override fun toDomain() =
        Quote(
            anime, character, quote
        )

}
