package com.focos_dengue.domain.service

import android.content.Context
import android.location.Address
import android.location.Geocoder
import android.os.Build
import com.focos_dengue.domain.model.AddressModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext
import java.util.Locale

class AddressService(private val context: Context) {
    suspend fun getAddressFromLatLng(lat: Double, lng: Double): AddressModel? {
        val address = withContext(Dispatchers.IO) {
            val geocoder = Geocoder(context, Locale.getDefault())

            try {
                // Verifica a versão do Android do usuário para usar a API correta
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    val address = suspendCancellableCoroutine { continuation ->
                        geocoder.getFromLocation(lat, lng, 1, object : Geocoder.GeocodeListener {
                            override fun onGeocode(addresses: MutableList<Address>) {
                                continuation.resume(addresses.firstOrNull()) { _, _, _ -> }
                            }

                            override fun onError(errorMessege: String?) {
                                continuation.resume(null) { _, _, _ -> }
                            }
                        })
                    }
                    return@withContext address
                }
                @Suppress("DEPRECATION")
                return@withContext geocoder.getFromLocation(lat, lng, 1)?.firstOrNull()
            }
            catch (_: Exception) {
                return@withContext null
            }
        }

        if (address == null)
            return null

        return AddressModel(
            neighborhood = address.subLocality,
            street = address.thoroughfare
        )
    }
}