package com.focos_dengue.domain.repository

import io.github.jan.supabase.gotrue.user.UserInfo

interface AuthRepository {

    suspend fun signUp(email: String, password: String): Result<Unit>

    suspend fun signIn(email: String, password: String): Result<Unit>

    suspend fun recoverPassword(email: String, redirectUrl: String): Result<Unit>

    suspend fun updatePassword(accesToken: String, refreshToken: String, newPassword: String): Result<Unit>

    suspend fun updateEmail(newEmail: String): Result<Unit>

    suspend fun logout()

    fun getCurrentUser(): UserInfo?
}