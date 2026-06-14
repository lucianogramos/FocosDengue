package com.focos_dengue.data.remote.dto

import com.focos_dengue.domain.model.Location
import com.focos_dengue.domain.model.ReportType
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ReportDto (
    @SerialName("type") val type: ReportType,
    @SerialName("description") val description: String,
    @SerialName("location") val location: Location,
    @SerialName("photo") val imageUrl: String,
    @SerialName("created_at") val createdAt: Long ? = null
)