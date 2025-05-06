package org.cmparchitecture.data.repository

interface AuthRepo {
    suspend fun signInWithEmail(email: String, password: String): Boolean
    suspend fun signupWithEmail(email: String, password: String) : Boolean
    suspend fun signOut()
}