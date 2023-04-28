package com.loperilla.datasource.di

import android.content.Context
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.loperilla.datasource.datastore.IUserDataStoreDataSource
import com.loperilla.datasource.datastore.UserDataStoreDataSourceImpl
import com.loperilla.datasource.firebase.auth.FirebaseAuthDataSourceImpl
import com.loperilla.datasource.firebase.auth.IFirebaseAuthDataSource
import com.loperilla.datasource.firebase.reference.CustomReference.SHOPPING_LIST_REFERENCE
import com.loperilla.model.database.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

/*****
 * Project: CompraCasa
 * From: com.loperilla.datasource.di
 * Created By Manuel Lopera on 23/4/23 at 14:03
 * All rights reserved 2023
 */

@Module
@InstallIn(SingletonComponent::class)
object DataSourceDependencyInjector {

    @Singleton
    @Provides
    fun providesPreferenceDataStore(
        context: Context
    ): IUserDataStoreDataSource = UserDataStoreDataSourceImpl(context)

    @Singleton
    @Provides
    fun provideContext(@ApplicationContext context: Context): Context {
        return context
    }

    @Singleton
    @Provides
    fun providesFirebaseAuth(
        userDataStore: IUserDataStoreDataSource
    ): IFirebaseAuthDataSource = FirebaseAuthDataSourceImpl(userDataStore)

    @Singleton
    @Provides
    fun providesShoppingListReference(
        @Named(Constants.SHOPPINGLIST) databaseReference: DatabaseReference
    ): SHOPPING_LIST_REFERENCE = SHOPPING_LIST_REFERENCE(databaseReference)

    @Named(Constants.SHOPPINGLIST)
    @Singleton
    @Provides
    fun providesShoppingListDatabase(): DatabaseReference =
        Firebase.database.reference.child(Constants.SHOPPINGLIST)
}
