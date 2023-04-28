package com.loperilla.data.model

import com.loperilla.model.database.ShoppingItem

data class ShoppingListFirestore(
    var id: String = "",
    var title: String = "",
    var date: String = ""
)

fun ShoppingListFirestore.toDomain(): ShoppingItem {
    return ShoppingItem(
        title = this.title,
        date = this.date
    )
}

fun ShoppingItem.toRemote(id: String): ShoppingListFirestore {
    return ShoppingListFirestore(
        id, title, date
    )
}