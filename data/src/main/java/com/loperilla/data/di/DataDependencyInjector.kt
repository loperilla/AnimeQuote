package com.loperilla.data.di

import com.loperilla.data.datastore.DataStoreRepository
import com.loperilla.data.firebase.auth.FirebaseAuthRepository
import com.loperilla.data.firebase.database.IShoppingList
import com.loperilla.data.firebase.database.ShoppingListRepository
import com.loperilla.datasource.datastore.UserDataStoreDataSourceImpl
import com.loperilla.datasource.firebase.auth.FirebaseAuthDataSourceImpl
import com.loperilla.datasource.firebase.reference.CustomReference.SHOPPING_LIST_REFERENCE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/*****
 * Project: CompraCasa
 * From: com.loperilla.data.di
 * Created By Manuel Lopera on 23/4/23 at 18:02
 * All rights reserved 2023
 */
@Module
@InstallIn(SingletonComponent::class)
object DataDependencyInjector {
    @Provides
    fun providesDataStoreRepository(
        userPref: UserDataStoreDataSourceImpl
    ): DataStoreRepository = DataStoreRepository(userPref)

    @Provides
    fun provideFirebaseAuth(
        firebaseAuth: FirebaseAuthDataSourceImpl
    ): FirebaseAuthRepository = FirebaseAuthRepository(firebaseAuth)

    @Provides
    fun provideShoppingListRepository(
        shoppingListReference: SHOPPING_LIST_REFERENCE,
        userPref: FirebaseAuthRepository
    ): IShoppingList = ShoppingListRepository(shoppingListReference, userPref)
}
