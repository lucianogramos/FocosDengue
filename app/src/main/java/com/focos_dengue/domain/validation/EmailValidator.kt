package com.focos_dengue.domain.validation

data class EmailRequirements(
    val hasAt: Boolean = false,
    val hasDomain: Boolean = false,
    val hasUser: Boolean = false,
    val hasNoSpaces: Boolean = false
) {
    val isValid: Boolean
        get() = hasAt &&
                hasDomain &&
                hasUser &&
                hasNoSpaces
}


object EmailValidator {

    fun validate(email: String): EmailRequirements {

        val parts = email.split("@")

        return EmailRequirements(
            hasAt = email.contains("@"),
            hasDomain = email.contains("."),
            hasUser = parts.firstOrNull()?.isNotBlank() == true,
            hasNoSpaces = !email.contains(" ")
        )
    }
}