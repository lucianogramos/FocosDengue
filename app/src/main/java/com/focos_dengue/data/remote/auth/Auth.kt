package com.focos_dengue.data.remote.auth

import com.focos_dengue.data.remote.supabase
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.gotrue.providers.builtin.Email

suspend fun cadastrarUsuario(email: String, senha: String) {
    supabase.auth.signUpWith(Email) {
        this.email = email
        this.password = senha
    }
}

suspend fun loginUsuario(email: String, senha: String) {
    supabase.auth.signInWith(Email) {
        this.email = email
        this.password = senha
    }
}

suspend fun logoutUsuario() {
    supabase.auth.signOut()
}

fun usuarioAtual() = supabase.auth.currentUserOrNull()