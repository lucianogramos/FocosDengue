package com.focos_dengue.domain.usecase

import android.content.Context
import android.location.Geocoder
import java.util.Locale

class GetAddressFromLatLngUseCase(
    private val context: Context
) {
    suspend operator fun invoke(latitude: Double, longitude: Double): String? {
        // Rodar em IO thread na prática
        val geocoder = Geocoder(context, Locale.getDefault())
        val addresses = geocoder.getFromLocation(latitude, longitude, 1)
        return addresses?.firstOrNull()?.getAddressLine(0)
    }
}
