package com.focos_dengue.domain.usecase

import com.focos_dengue.domain.model.Location
import com.focos_dengue.domain.model.Report
import com.focos_dengue.domain.model.ReportType
import com.focos_dengue.domain.repository.ImageRepository
import com.focos_dengue.domain.repository.ReportRepository

class SubmitReportUseCase(
    private val reportRepository: ReportRepository,
    private val imageRepository: ImageRepository
) {

    suspend operator fun invoke(
        type: ReportType,
        description: String,
        location: Location,
        localImagePath: String
    ): Result<Unit> {
        val imageUrl = imageRepository.uploadImage(localImagePath)

        val report = Report(
            type = type,
            description = description,
            location = location,
            imageUrl = imageUrl
        )

        return reportRepository.submitReport(report)
    }
}