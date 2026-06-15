package com.focos_dengue.domain.usecase

import android.net.Uri
import com.focos_dengue.domain.model.Location
import com.focos_dengue.domain.model.Report
import com.focos_dengue.domain.model.ReportType
import com.focos_dengue.domain.repository.ImageRepository
import com.focos_dengue.domain.repository.ReportRepository
import androidx.core.net.toUri

class SubmitReportUseCase(
    private val reportRepository: ReportRepository,
    private val imageRepository: ImageRepository
) {
    suspend operator fun invoke(
        type: ReportType,
        description: String,
        location: Location,
        localImagePath: Uri
    ): Result<Unit> {
        val imageUrl = imageRepository.uploadImage(localImagePath)

        val report = Report(
            type = type,
            description = description,
            location = location,
            imageUrl = imageUrl.toUri()
        )

        return reportRepository.submitReport(report)
    }
}