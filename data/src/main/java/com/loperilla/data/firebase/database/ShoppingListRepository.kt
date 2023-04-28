package com.loperilla.data.firebase.database

import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.loperilla.data.firebase.auth.FirebaseAuthRepository
import com.loperilla.data.model.ShoppingListFirestore
import com.loperilla.data.model.toDomain
import com.loperilla.data.model.toRemote
import com.loperilla.datasource.firebase.reference.CustomReference.SHOPPING_LIST_REFERENCE
import com.loperilla.model.database.Constants.ITEMS
import com.loperilla.model.database.DatabaseResult
import com.loperilla.model.database.ShoppingItem
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

/*****
 * Project: CompraCasa
 * From: com.loperilla.data.common.firebase.database
 * Created By Manuel Lopera on 25/4/23 at 19:36
 * All rights reserved 2023
 */
class ShoppingListRepository @Inject constructor(
    private val reference: SHOPPING_LIST_REFERENCE,
    private val userPref: FirebaseAuthRepository
) : IShoppingList {
    override suspend fun getUid() = userPref.getCurrentUserAuth()?.uid ?: ""

    override suspend fun getAllIModel(): Flow<DatabaseResult<ShoppingItem>> = flow {
        val uid = getUid().ifEmpty {
            emit(DatabaseResult.FAIL("Empty UID"))
            return@flow
        }
        val deferred = CompletableDeferred<DatabaseResult<ShoppingItem>>()
        suspendCancellableCoroutine { continuation ->
            val listener = object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val returnList = mutableListOf<ShoppingItem>()
                    for (childSnapshot in dataSnapshot.children) {
                        val shoppingListItem = childSnapshot.getValue(ShoppingListFirestore::class.java)
                        if (shoppingListItem != null) {
                            returnList.add(shoppingListItem.toDomain())
                        }
                    }
                    deferred.complete(DatabaseResult.GET(returnList))
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    Log.e("getAllIModel", databaseError.message)
                    deferred.complete(DatabaseResult.FAIL(databaseError.message))
                }
            }

            val query = reference.shoppingReference.child(uid).child(ITEMS)
            query.addListenerForSingleValueEvent(listener)
            continuation.invokeOnCancellation {
                query.removeEventListener(listener)
                deferred.cancel()
            }
            continuation.resumeWith(Result.success(Unit))
        }
        emit(deferred.await())
    }

    override suspend fun getSingleIModel(itemId: String): DatabaseResult<ShoppingItem> {
        val uid = getUid().ifEmpty {
            return DatabaseResult.FAIL("Empty UID")
        }
        val snapshot =
            reference.shoppingReference.child(uid).child(ITEMS).orderByChild("id").equalTo(itemId).get().await()
        val item = snapshot.children.firstOrNull()?.getValue(ShoppingListFirestore::class.java)
        return if (item != null) {
            DatabaseResult.GET_SINGLE(item.toDomain())
        } else {
            DatabaseResult.FAIL("Item not found")
        }
    }

    override suspend fun addSingleIModel(item: ShoppingItem): DatabaseResult<ShoppingItem> {
        val uid = getUid().ifEmpty {
            return DatabaseResult.FAIL("Empty UID")
        }
        val newItemRef: DatabaseReference = reference.shoppingReference.child(uid).child(ITEMS).push()
        val newItem: ShoppingListFirestore? = newItemRef.key?.let { item.toRemote(id = it) }
        newItemRef.setValue(newItem).await()
        return DatabaseResult.GET_SINGLE((newItem ?: ShoppingListFirestore()).toDomain())
    }

    override suspend fun deleteSingleIModel(itemId: String): DatabaseResult<String> {
        val uid = getUid().ifEmpty {
            return DatabaseResult.FAIL("Empty UID")
        }
        val itemRef = reference.shoppingReference.child(uid).child(ITEMS).orderByChild("id").equalTo(itemId).get()
            .await().children.firstOrNull()?.ref
        return if (itemRef != null) {
            itemRef.removeValue().await()
            DatabaseResult.GET_SINGLE("")
        } else {
            DatabaseResult.FAIL("Item not found")
        }
    }
}
