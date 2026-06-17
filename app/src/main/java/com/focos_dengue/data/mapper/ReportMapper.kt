package com.focos_dengue.data.mapper

import com.focos_dengue.data.remote.dto.ReportDto
import com.focos_dengue.domain.model.Report
import androidx.core.net.toUri

fun Report.toDto(): ReportDto {

    return ReportDto(
        description = this.description ?: "",
        locationDto = this.location.toDto(),
        imageUrl = this.imageUri.toString(),
        type = this.type,
        createdAt = this.createdAt
    )
}

fun ReportDto.toModel(): Report {

    return Report(
        description = this.description,
        location = this.locationDto.toModel(),
        imageUri = this.imageUrl.toUri(),
        type = this.type,
        createdAt = this.createdAt
    )
}