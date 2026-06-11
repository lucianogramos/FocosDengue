package backend.domain.model

data class Report(
    val id: String? = null,
    val type: ReportType,
    val description: String,
    val location: Location,
    val imageUrls: List<String> = emptyList(),
    val createdAt: Long = System.currentTimeMillis()
)