package com.focos_dengue.domain.repository

import com.focos_dengue.domain.model.Location

interface LocationRepository {

    suspend fun getLocation(
        latitude: Double,
        longitude: Double
    ): Location

}