package com.focos_dengue.domain.usecase

import com.focos_dengue.domain.model.AddressModel
import com.focos_dengue.domain.repository.GeoLocationRepository

class GetAddressFromLatLngUseCase(
    private val geoLocationRepository: GeoLocationRepository
) {

    suspend operator fun invoke(latitude: Double, longitude: Double): AddressModel {
        return geoLocationRepository.getAdress(latitude, longitude)
    }
}