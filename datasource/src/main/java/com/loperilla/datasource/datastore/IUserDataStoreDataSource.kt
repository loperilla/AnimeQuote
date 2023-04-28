package com.loperilla.datasource.datastore

import androidx.datastore.preferences.core.Preferences
import kotlinx.coroutines.flow.Flow

/*****
 * Project: CompraCasa
 * From: com.loperilla.data.datastore
 * Created By Manuel Lopera on 21/4/23 at 13:30
 * All rights reserved 2023
 */

interface IUserDataStoreDataSource {
    fun getAll(): Flow<Preferences>
    fun getString(key: String): Flow<String>
    suspend fun insertString(key: String, value: String)
}