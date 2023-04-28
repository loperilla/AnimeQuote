package com.loperilla.datasource.firebase.auth

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.loperilla.datasource.datastore.DataStoreUtils.Constants.UID_PREFERENCE
import com.loperilla.datasource.datastore.IUserDataStoreDataSource
import com.loperilla.model.auth.AuthResult
import com.loperilla.model.auth.AuthResult.AuthFail
import com.loperilla.model.auth.AuthResult.AuthSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FirebaseAuthDataSourceImpl @Inject constructor(
    private val dataStore: IUserDataStoreDataSource
) : IFirebaseAuthDataSource {
    private val TAG = FirebaseAuthDataSourceImpl::class.java.simpleName
    private val auth: FirebaseAuth
        get() = Firebase.auth

    private var currentAuthUser: FirebaseUser? = null
        private set(value) {
            field = value
            runBlocking {
                saveAuthToken(value)
            }
        }

    private suspend fun saveAuthToken(firebaseUser: FirebaseUser?) = withContext(Dispatchers.IO) {
        if (firebaseUser != null) {
            dataStore.insertString(UID_PREFERENCE, firebaseUser.uid)
        }
    }

    override suspend fun doFirebaseLogin(
        email: String,
        password: String
    ): AuthResult {
        return try {
            this@FirebaseAuthDataSourceImpl.currentAuthUser =
                auth.signInWithEmailAndPassword(email, password).await().user
            AuthSuccess(currentAuthUser?.uid!!)
        } catch (ex: Exception) {
            AuthFail(ex.message ?: "Hubo un error")
        }
    }

    override fun getUserAuth(): FirebaseUser? = this.auth.currentUser

//    override suspend fun doFirebaseRegister(
//        dataRegistration: DataRegistration,
//        callback: ResultCallback
//    ) {
//        auth.createUserWithEmailAndPassword(dataRegistration.email, dataRegistration.password)
//            .addOnCompleteListener { task ->
//                if (task.isSuccessful) {
//                    currentAuthUser = auth.currentUser
//                    currentAuthUser!!.updateProfile(
//                        UserProfileChangeRequest.Builder()
//                            .setDisplayName(dataRegistration.displayName)
//                            .build()
//                    )
//                    callback.onSuccessfulResult()
//                } else {
//                    task.exception?.printStackTrace()
//                    callback.onFailureResult(
//                        task.exception?.stackTraceToString() ?: "Hubo un error"
//                    )
//                }
//            }
//    }

}
