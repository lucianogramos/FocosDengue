package com.focos_dengue.domain.validation

data class PasswordRequirements(
    val hasMinChars: Boolean = false,
    val hasUpperCase: Boolean = false,
    val hasLowerCase: Boolean = false,
    val hasNumber: Boolean = false,
    val hasSpecialChar: Boolean = false
) {
    val isValid: Boolean
        get() = hasMinChars && hasUpperCase && hasLowerCase && hasNumber && hasSpecialChar
}

object PasswordValidator {
    fun validate(password: String): PasswordRequirements {
        return PasswordRequirements(
            hasMinChars = password.length >= 8,
            hasUpperCase = password.any { it.isUpperCase() },
            hasLowerCase = password.any { it.isLowerCase() },
            hasNumber = password.any { it.isDigit() },
            hasSpecialChar = password.any { !it.isLetterOrDigit() }
        )
    }
}