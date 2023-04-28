package com.loperilla.onboarding_domain.usecase

import com.loperilla.data.firebase.database.ShoppingListRepository
import com.loperilla.model.database.DatabaseResult
import com.loperilla.model.database.ShoppingItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/*****
 * Project: CompraCasa
 * From: com.loperilla.onboarding_domain.usecase
 * Created By Manuel Lopera on 25/4/23 at 20:38
 * All rights reserved 2023
 */
class HomeUseCase @Inject constructor(
    private val shoppingListRepository: ShoppingListRepository
) {
    suspend fun getUserShoppingList(): Flow<DatabaseResult<ShoppingItem>> =
        shoppingListRepository.getAllIModel()
}