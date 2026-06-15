package com.focos_dengue.data.repository

import com.focos_dengue.data.remote.AuthDataSource
import com.focos_dengue.data.remote.supabase
import com.focos_dengue.domain.repository.AuthRepository
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.gotrue.providers.builtin.Email

class AuthRepositoryImpl(private val authDataSource: AuthDataSource) : AuthRepository {

    override suspend fun signUp(email: String, password: String): Result<Unit> {

        return try {
            authDataSource.signUp(email, password)

            Result.success(Unit)

        } catch (e: Exception) {
            Result.failure(e)
        }

    }

    override suspend fun signIn(email: String, password: String): Result<Unit> {


        return try {
            authDataSource.signIn(email, password)

            Result.success(Unit)

        } catch (e: Exception) {
            Result.failure(e)
        }

    }

    override suspend fun recoverPassword(email: String): Result<Unit> {

        return try {

            authDataSource.recoverPassword(email)

            Result.success(Unit)

        } catch (e: Exception) {
            Result.failure(e)
        }

    }
    override suspend fun importAuthToken(accesToken: String, refreshToken: String): Result<Unit> {

        return try {

            authDataSource.importAuthToken(accesToken, refreshToken)

            Result.success(Unit)

        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun updatePassword(newPassword: String): Result<Unit> {

        return try {

            authDataSource.updatePassword(newPassword)

            Result.success(Unit)

        } catch (e: Exception) {
            Result.failure(e)
        }

    }

    override suspend fun logout() {
        authDataSource.logout()
    }

    override fun getCurrentUser() = authDataSource.currentUser()
}