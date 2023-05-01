package com.loperilla.model.quote

/*****
 * Project: ComposeAnime
 * From: com.loperilla.model.quote
 * Created By Manuel Lopera on 1/5/23 at 18:39
 * All rights reserved 2023
 */
data class Character(
    val name: String
) {
    fun doesMatchSearchQuery(query: String): Boolean {
        name.ifEmpty {
            return false
        }
        val matchingCombinations = listOf(
            name,
            name.first(),
        )

        return matchingCombinations.any {
            name.contains(query, ignoreCase = true)
        }
    }
}
