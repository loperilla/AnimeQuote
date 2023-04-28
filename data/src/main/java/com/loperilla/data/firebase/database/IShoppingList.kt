package com.loperilla.data.firebase.database

import com.loperilla.model.database.DatabaseResult
import com.loperilla.model.database.ShoppingItem
import kotlinx.coroutines.flow.Flow

/*****
 * Project: CompraCasa
 * From: com.loperilla.datasource.di
 * Created By Manuel Lopera on 25/4/23 at 19:33
 * All rights reserved 2023
 */
interface IShoppingList {
    suspend fun getUid(): String?
    suspend fun getAllIModel(): Flow<DatabaseResult<ShoppingItem>>
    suspend fun getSingleIModel(itemId: String): DatabaseResult<ShoppingItem>
    suspend fun addSingleIModel(item: ShoppingItem): DatabaseResult<ShoppingItem>
    suspend fun deleteSingleIModel(itemId: String): DatabaseResult<String>
}
