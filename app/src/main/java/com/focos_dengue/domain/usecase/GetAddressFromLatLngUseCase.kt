package com.focos_dengue.domain.usecase

import com.focos_dengue.domain.model.Location
import com.focos_dengue.domain.repository.LocationRepository

class GetAddressFromLatLngUseCase(
    private val repository: LocationRepository
) {

    suspend operator fun invoke(
        latitude: Double,
        longitude: Double
    ): Location {

        return repository.getLocation(
            latitude,
            longitude
        )
    }
}