package com.focos_dengue.data.mapper

import androidx.core.net.toUri
import com.focos_dengue.data.remote.dto.ReportDto
import com.focos_dengue.domain.model.ReportModel
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

@OptIn(ExperimentalTime::class)
fun ReportModel.toDto(): ReportDto {

    return ReportDto(
        description = this.description ?: "",
        locationDto = this.location.toDto(),
        imageUrl = this.imageUri.toString(),
        type = this.type,
        createdAt = this.createdAt?.toString()
    )
}

@OptIn(ExperimentalTime::class)
fun ReportDto.toModel(): ReportModel {

    return ReportModel(
        description = this.description,
        location = this.locationDto.toModel(),
        imageUri = this.imageUrl.toUri(),
        type = this.type,
        createdAt = if (this.createdAt != null) Instant.parse(this.createdAt) else null
    )
}