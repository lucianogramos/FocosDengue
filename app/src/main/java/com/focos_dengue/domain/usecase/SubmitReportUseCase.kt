package com.focos_dengue.domain.usecase

import com.focos_dengue.domain.model.Location
import com.focos_dengue.domain.model.Report
import com.focos_dengue.domain.model.ReportType
import com.focos_dengue.domain.repository.ImageRepository
import com.focos_dengue.domain.repository.ReportRepository

class SubmitReportUseCase(
    private val repository: ReportRepository,
    private val imageRepository: ImageRepository
) {
    suspend operator fun invoke(
        type: ReportType,
        description: String,
        location: Location,
        localImagePaths: List<String>
    ): Result<Report> {
        // Upload de imagens
        val imageResult = imageRepository.uploadImages(localImagePaths)
        val imageUrl = imageResult.getOrElse { emptyList() }

        val report = Report(
            type = type,
            description = description,
            location = location.copy(address = location.address ?: location.address),
            imageUrl = imageUrl
        )

        return repository.submitReport(report)
    }
}