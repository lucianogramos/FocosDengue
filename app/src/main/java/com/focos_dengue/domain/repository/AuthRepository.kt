package com.focos_dengue.domain.repository

import io.github.jan.supabase.gotrue.user.UserInfo

interface AuthRepository {

    suspend fun signUp(
        email: String,
        password: String
    ): Result<Unit>


    suspend fun signIn(
        email: String,
        password: String
    ): Result<Unit>


    suspend fun recoverPassword(
        email: String
    ): Result<Unit>

    suspend fun importAuthToken(accesToken: String, refreshToken: String): Result<Unit>
    suspend fun updatePassword(
        newPassword: String
    ): Result<Unit>


    suspend fun logout()


    fun getCurrentUser(): UserInfo?
}