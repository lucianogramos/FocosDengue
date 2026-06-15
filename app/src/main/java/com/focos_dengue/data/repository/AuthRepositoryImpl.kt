package com.focos_dengue.data.repository

import com.focos_dengue.data.remote.AuthDataSource
import com.focos_dengue.domain.repository.AuthRepository

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

    override suspend fun recoverPassword(email: String, redirectUrl: String): Result<Unit> {

        return try {

            authDataSource.recoverPassword(email, redirectUrl)

            Result.success(Unit)

        } catch (e: Exception) {
            Result.failure(e)
        }

    }

    override suspend fun updateEmail(redirectUrl: String, newEmail: String): Result<Unit> {

        return try {

            authDataSource.updateEmail(redirectUrl, newEmail)

            Result.success(Unit)

        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun updatePassword(accesToken: String?, refreshToken: String?, redirectUrl: String, newPassword: String): Result<Unit> {

        return try {

            authDataSource.updatePassword(accesToken, refreshToken, redirectUrl, newPassword)

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