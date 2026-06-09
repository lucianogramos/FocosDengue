package com.focos_dengue.domain.repository

interface AuthRepository {
    suspend fun cadastrarUsuario(email: String, senha: String): Result<Unit>
    suspend fun loginUsuario(email: String, senha: String): Result<Unit>
    suspend fun recoverPassword(email: String): Result<Unit>
    suspend fun logoutUsuario()
}