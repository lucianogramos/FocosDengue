package com.focos_dengue.data.remote.location

import android.content.Context
import android.location.Geocoder
import com.focos_dengue.domain.model.Location
import java.util.Locale

class MapsLocationDataSource(
    private val context: Context
) {

    suspend fun getLocationFromLatLng(
        latitude: Double,
        longitude: Double
    ): Location {

        val geocoder = Geocoder(
            context,
            Locale.getDefault()
        )

        val address = geocoder
            .getFromLocation(
                latitude,
                longitude,
                1
            )
            ?.firstOrNull()
            ?.getAddressLine(0)

        return Location(
            latitude = latitude,
            longitude = longitude,
            address = address
        )
    }
}