package com.focos_dengue.data.mapper

import com.focos_dengue.data.remote.dto.ReportDto
import com.focos_dengue.domain.model.Report

fun Report.toDto(): ReportDto {

    return ReportDto(
        description = this.description,
        location = this.location,
        imageUrl = this.imageUrl,
        type = this.type,
        createdAt = this.createdAt
    )
}

fun Report.toModel(): Report {

    return Report(
        description = this.description,
        location = this.location,
        imageUrl = this.imageUrl,
        type = this.type,
        createdAt = this.createdAt
    )
}