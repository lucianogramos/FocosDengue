package com.focos_dengue.domain.model

import android.net.Uri

data class ReportModel(
    val type: ReportType,
    val description: String?,
    val location: LocationModel,
    val imageUri: Uri,
    val createdAt: Long? = null
)