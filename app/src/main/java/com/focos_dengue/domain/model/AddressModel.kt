package com.focos_dengue.domain.model

data class AddressModel (
    val neighborhood: String = "",
    val street: String = "",
    val number: String = "",
    val city: String = "",
    val state: String = ""
) {
    override fun toString(): String {
        return "$street N°$number - $neighborhood\n$city - $state"
    }
}