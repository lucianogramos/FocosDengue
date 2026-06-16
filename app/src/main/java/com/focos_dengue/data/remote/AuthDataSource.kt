package com.focos_dengue.data.remote

import io.github.jan.supabase.gotrue.Auth
import io.github.jan.supabase.gotrue.providers.builtin.Email
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.rpc

class AuthDataSource(
    private val auth: Auth,
    private val postgrest: Postgrest
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

    suspend fun recoverPassword(email: String, redirectUrl: String) {
        auth.resetPasswordForEmail(email = email, redirectUrl = redirectUrl)
    }

    suspend fun updateEmail(redirectUrl: String, newEmail: String) {
        auth.modifyUser(redirectUrl = redirectUrl) {
            email = newEmail
        }
        this.logout()
    }

    suspend fun updatePassword(accessToken: String? = null, refreshToken: String? = null, redirectUrl: String, newPassword: String) {

        if (accessToken != null && refreshToken != null) {
            auth.importAuthToken(accessToken, refreshToken)
        }
        auth.modifyUser(redirectUrl = redirectUrl) {
            password = newPassword
        }
        this.logout()
    }

    suspend fun deleteAccount() {
        postgrest.rpc("delete_account")
    }

    suspend fun logout() {
        auth.signOut()
    }

    fun currentUser() = auth.currentUserOrNull()
}