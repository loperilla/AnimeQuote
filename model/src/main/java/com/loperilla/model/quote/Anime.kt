package com.loperilla.model.quote

/*****
 * Project: ComposeAnime
 * From: com.loperilla.model.quote
 * Created By Manuel Lopera on 1/5/23 at 11:10
 * All rights reserved 2023
 */
data class Anime(
    val name: String
) {
    fun doesMatchSearchQuery(query: String): Boolean {
        val matchingCombinations = listOf(
            name,
            name.first(),
        )

        return matchingCombinations.any {
            name.contains(query, ignoreCase = true)
        }
    }
}
