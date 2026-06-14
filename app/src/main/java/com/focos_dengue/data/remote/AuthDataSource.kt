package com.focos_dengue.data.remote

import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.gotrue.providers.builtin.Email

class AuthDataSource {

    suspend fun signUp(email: String, password: String) {
        supabase.auth.signUpWith(Email) {
            this.email = email
            this.password = password
        }
    }

    suspend fun signIn(email: String, password: String) {
        supabase.auth.signInWith(Email) {
            this.email = email
            this.password = password
        }
    }

    suspend fun recoverPassword(email: String) {
        supabase.auth.resetPasswordForEmail(email = email)
    }

    suspend fun updatePassword(newPassword: String) {
        supabase.auth.modifyUser {
            password = newPassword
        }
    }

    suspend fun logout() {
        supabase.auth.signOut()
    }

    fun currentUser() = supabase.auth.currentUserOrNull()
}