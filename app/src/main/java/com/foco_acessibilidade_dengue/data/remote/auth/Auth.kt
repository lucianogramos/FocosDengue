package com.foco_acessibilidade_dengue.data.remote.auth

import com.foco_acessibilidade_dengue.data.remote.supabase
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.gotrue.providers.builtin.Email

suspend fun cadastrarUsuario(email: String, senha: String): Result<Unit> {

    if (email.isBlank()) {
        return Result.failure(Exception("Digite um e-mail!"))
    }
    if (senha.isBlank()) {
        return Result.failure(Exception("Digite uma senha!"))
    }
    if (senha.length < 6) {
        return Result.failure(Exception("A senha deve conter pelo menos 6 caracteres"))
    }

    return try {
        supabase.auth.signUpWith(Email) {
            this.email = email
            this.password = senha
        }

        Result.success(Unit)

    } catch (e: Exception) {
        Result.failure(e)
    }

}

suspend fun loginUsuario(email: String, senha: String): Result<Unit> {

    if (email.isBlank()) {
        return Result.failure(Exception("Digite um e-mail!"))
    }
    if (senha.isBlank()) {
        return Result.failure(Exception("Digite uma senha!"))
    }

    return try {
        supabase.auth.signInWith(Email) {
            this.email = email
            this.password = senha
        }

        Result.success(Unit)

    } catch (e: Exception) {
        Result.failure(e)
    }

}

suspend fun logoutUsuario() {
    supabase.auth.signOut()
}

fun usuarioAtual() = supabase.auth.currentUserOrNull()