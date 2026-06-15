package com.focos_dengue.domain.model

import android.net.Uri

data class Report(
    val type: ReportType,
    val description: String,
    val location: Location,
    val imageUrl: Uri?,
    val createdAt: Long = System.currentTimeMillis()
)