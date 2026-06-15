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

    suspend fun signIn(redirectUrl: String, email: String, password: String) {
        auth.signInWith(Email, redirectUrl) {
            this.email = email
            this.password = password
        }
    }

    suspend fun recoverPassword(email: String, redirectUrl: String) {
        auth.resetPasswordForEmail(email = email, redirectUrl = redirectUrl)
    }

    suspend fun updateEmail(redirectUrl: String, newEmail: String) {
        auth.modifyUser(redirectUrl = redirectUrl) {
            email = newEmail
        }
        this.logout()
    }

    suspend fun updatePassword(accesToken: String? = null, refreshToken: String? = null, redirectUrl: String, newPassword: String) {

        if (accesToken != null && refreshToken != null) {
            auth.importAuthToken(accesToken, refreshToken)
        }
        auth.modifyUser(redirectUrl = redirectUrl) {
            password = newPassword
        }
        this.logout()
    }

    suspend fun logout() {
        auth.signOut()
    }

    fun currentUser() = auth.currentUserOrNull()
}