package com.app.instagram.common.firebase

import com.app.instagram.common.AuthManager
import com.app.instagram.common.toUnit
import com.app.instagram.data.firebase.common.auth
import com.google.android.gms.tasks.Task

class FirebaseAuthManager : AuthManager {
    override fun signOut() {
        auth.signOut()
    }

    override fun signIn(email: String, password: String): Task<Unit> =
        auth.signInWithEmailAndPassword(email, password).toUnit()
}