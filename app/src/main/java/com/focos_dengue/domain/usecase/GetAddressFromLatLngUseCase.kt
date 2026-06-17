package com.focos_dengue.domain.usecase

import com.focos_dengue.domain.model.AddressModel
import com.focos_dengue.domain.service.AddressService

class GetAddressFromLatLngUseCase(
    private val addressService: AddressService
) {

    suspend operator fun invoke(latitude: Double, longitude: Double): AddressModel? {
        return addressService.getAddressFromLatLng(latitude, longitude)
    }
}