package com.focos_dengue.data.remote.dto

import com.focos_dengue.domain.model.ReportType
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ReportDto (
    @SerialName("id") val id: String? = null,
    @SerialName("type") val type: ReportType,
    @SerialName("description") val description: String,
    @SerialName("location") val locationDto: LocationDto,
    @SerialName("photo") val imageUrl: String,
    @SerialName("created_at") val createdAt: String? = null
)