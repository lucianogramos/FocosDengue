package com.focos_dengue.data.remote

import io.github.jan.supabase.gotrue.Auth
import io.github.jan.supabase.gotrue.providers.builtin.Email

class AuthDataSource(
    private val auth: Auth
) {

    suspend fun signUp(email: String, password: String) {
        auth.signUpWith(Email) {
            this.email = email
            this.password = password
        }
    }

    suspend fun signIn(email: String, password: String) {
        auth.signInWith(Email) {
            this.email = email
            this.password = password
        }
    }

    suspend fun recoverPassword(email: String) {
        auth.resetPasswordForEmail(email = email)
    }

    suspend fun importAuthToken(accesToken: String, refreshToken: String) {
        auth.importAuthToken(accesToken, refreshToken)
    }

    suspend fun updatePassword(newPassword: String) {
        auth.modifyUser {
            password = newPassword
        }
    }

    suspend fun logout() {
        auth.signOut()
    }

    fun currentUser() = auth.currentUserOrNull()
}