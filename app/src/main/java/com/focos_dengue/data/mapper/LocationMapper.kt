package com.focos_dengue.data.mapper

import com.focos_dengue.data.remote.dto.LocationDto
import com.focos_dengue.domain.model.Location
fun Location.toDto(): LocationDto {

    return LocationDto(
        latitude = this.latitude,
        longitude = this.longitude,
        address = this.address
    )
}

fun LocationDto.toModel(): Location {

    return Location(
        latitude = this.latitude,
        longitude = this.longitude,
        address = this.address
    )
}