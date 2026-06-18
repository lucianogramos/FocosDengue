package com.focos_dengue.domain.model

data class LocationModel(
    val latitude: Double,
    val longitude: Double,
    val address: AddressModel
)
