package com.focos_dengue.domain.repository

import com.focos_dengue.domain.model.AddressModel

interface GeoLocationRepository {
    suspend fun getAdress(lat: Double, lng: Double): AddressModel
}