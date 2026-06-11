package com.focos_dengue.data.remote.auth

import com.focos_dengue.data.remote.supabase
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.gotrue.providers.builtin.Email

suspend fun signUpUser(email: String, password: String): Result<Unit> {

    if (email.isBlank()) {
        return Result.failure(Exception("Digite um e-mail!"))
    }
    if (password.isBlank()) {
        return Result.failure(Exception("Digite uma senha!"))
    }
    if (password.length < 6) {
        return Result.failure(Exception("A senha deve conter pelo menos 6 caracteres"))
    }

    return try {
        supabase.auth.signUpWith(Email) {
            this.email = email
            this.password = password
        }

        Result.success(Unit)

    } catch (e: Exception) {
        Result.failure(e)
    }

}

suspend fun signInUser(email: String, password: String): Result<Unit> {

    if (email.isBlank()) {
        return Result.failure(Exception("Digite um e-mail!"))
    }
    if (password.isBlank()) {
        return Result.failure(Exception("Digite uma senha!"))
    }

    return try {
        supabase.auth.signInWith(Email) {
            this.email = email
            this.password = password
        }

        Result.success(Unit)

    } catch (e: Exception) {
        Result.failure(e)
    }

}

suspend fun recoverPassword(email: String): Result<Unit> {

    if (email.isBlank()) {
        return Result.failure(Exception("Digite um e-mail!"))
    }

    return try {

        supabase.auth.resetPasswordForEmail(email = email)

        Result.success(Unit)

    } catch (e: Exception) {
        Result.failure(e)
    }
}

suspend fun updateUser(newPassword: String): Result<Unit> {

    if (newPassword.isBlank()) {
        return Result.failure(Exception("Digite uma senha!"))
    }

    if (newPassword.length < 6) {
        return Result.failure(Exception("A senha deve conter pelo menos 6 caracteres"))
    }

    return try {

        supabase.auth.modifyUser {
            password = newPassword
        }

        Result.success(Unit)

    } catch (e: Exception) {
        Result.failure(e)
    }
}

suspend fun logoutUsuario() {
    supabase.auth.signOut()
}

fun currentUser() = supabase.auth.currentUserOrNull()