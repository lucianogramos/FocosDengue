package com.focos_dengue.domain.model

import android.net.Uri
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

data class ReportModel @OptIn(ExperimentalTime::class) constructor(
    val type: ReportType,
    val description: String?,
    val location: LocationModel,
    val imageUri: Uri,
    val createdAt: Instant? = null
)