package org.cmparchitecture.data.repository

import dev.gitlive.firebase.auth.FirebaseAuth

class AuthRepoImpl(private val auth: FirebaseAuth):AuthRepo {
    override suspend fun signInWithEmail(email: String, password: String): Boolean {
        return runCatching {
            auth.signInWithEmailAndPassword(email, password)
        }.isSuccess
    }

    override suspend fun signupWithEmail(email: String, password: String): Boolean {
        return runCatching {
            auth.createUserWithEmailAndPassword(email, password)
        }.isSuccess
    }

    override suspend fun signOut() {
        auth.signOut()
    }
}