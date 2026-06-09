package com.focos_dengue.domain.usecase

import com.focos_dengue.domain.repository.AuthRepository

class SignUpUseCase(private val repository: AuthRepository) {
    suspend operator fun invoke(email: String, password: String): Result<Unit> {
        return repository.cadastrarUsuario(email, password)
    }
}