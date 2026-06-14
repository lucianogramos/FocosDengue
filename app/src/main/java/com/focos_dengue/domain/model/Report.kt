package com.focos_dengue.domain.model

data class Report(
    val type: ReportType,
    val description: String,
    val location: Location,
    val imageUrl: List<String>,
    val createdAt: Long = System.currentTimeMillis()
)