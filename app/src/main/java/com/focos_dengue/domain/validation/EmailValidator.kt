package com.focos_dengue.domain.validation

object EmailValidator {

    fun validate(email: String): Boolean {
        val parts = email.split("@")

        return email.contains("@") && email.contains(".") && !email.endsWith(".") &&
            parts.firstOrNull()?.isNotBlank() == true && !email.contains(" ")
    }
}