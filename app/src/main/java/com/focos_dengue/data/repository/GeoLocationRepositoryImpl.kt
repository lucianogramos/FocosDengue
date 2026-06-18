package com.focos_dengue.data.repository

import android.location.Address
import android.location.Geocoder
import android.os.Build
import com.focos_dengue.domain.model.AddressModel
import com.focos_dengue.domain.repository.GeoLocationRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext

class GeoLocationRepositoryImpl(private val geocoder: Geocoder) : GeoLocationRepository {
    override suspend fun getAdress(lat: Double, lng: Double): AddressModel {
        val address = withContext(Dispatchers.IO) {
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

        if (address == null) {
            return AddressModel(
                neighborhood = "Bairro não encontrado",
                street = "Rua não encontrada",
                number = "Número não encontrado",
                city = "Cidade não encontrada",
                state = "Estado não encontrado"
            )
        }

        return AddressModel(
            neighborhood = address.subLocality ?: "Bairro não encontrado",
            street = address.thoroughfare ?: "Rua não encontrada",
            number = address.subThoroughfare ?: "Número não encontrado",
            city = address.locality ?:
                address.subAdminArea ?:
                address.subLocality ?:
                "Cidade não encontrada",
            state = address.adminArea ?: "Estado não encontrado"
        )
    }
}