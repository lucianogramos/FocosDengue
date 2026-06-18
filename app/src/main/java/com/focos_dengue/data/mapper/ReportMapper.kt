package com.focos_dengue.data.mapper

import androidx.core.net.toUri
import com.focos_dengue.data.remote.dto.ReportDto
import com.focos_dengue.domain.model.ReportModel

fun ReportModel.toDto(): ReportDto {

    return ReportDto(
        description = this.description ?: "",
        locationDto = this.location.toDto(),
        imageUrl = this.imageUri.toString(),
        type = this.type,
        createdAt = this.createdAt
    )
}

fun ReportDto.toModel(): ReportModel {

    return ReportModel(
        description = this.description,
        location = this.locationDto.toModel(),
        imageUri = this.imageUrl.toUri(),
        type = this.type,
        createdAt = this.createdAt
    )
}