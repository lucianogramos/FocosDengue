package com.focos_dengue.data.repository

import com.focos_dengue.data.remote.MapsLocationDataSource
import com.focos_dengue.domain.model.Location
import com.focos_dengue.domain.repository.LocationRepository

class LocationRepositoryImpl(
    private val mapsLocationDataSource: MapsLocationDataSource
) : LocationRepository {

    override suspend fun getLocation(
        latitude: Double,
        longitude: Double
    ): Location {

        return mapsLocationDataSource.getLocationFromLatLng(
            latitude,
            longitude
        )
    }
}