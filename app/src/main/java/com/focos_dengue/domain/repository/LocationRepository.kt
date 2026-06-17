package com.focos_dengue.domain.repository

import com.focos_dengue.domain.model.LocationModel

interface LocationRepository {

    suspend fun getLocation(
        latitude: Double,
        longitude: Double
    ): LocationModel

}