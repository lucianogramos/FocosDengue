package com.focos_dengue.data.mapper

import com.focos_dengue.data.remote.dto.LocationDto
import com.focos_dengue.domain.model.AddressModel
import com.focos_dengue.domain.model.LocationModel
fun LocationModel.toDto(): LocationDto {

    return LocationDto(
        latitude = this.latitude,
        longitude = this.longitude
    )
}

fun LocationDto.toModel(): LocationModel {

    return LocationModel(
        latitude = this.latitude,
        longitude = this.longitude,
        address = AddressModel("", "")
    )
}