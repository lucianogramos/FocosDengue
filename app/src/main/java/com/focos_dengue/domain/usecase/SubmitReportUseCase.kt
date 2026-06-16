package com.focos_dengue.domain.usecase

import androidx.core.net.toUri
import com.focos_dengue.domain.model.Report
import com.focos_dengue.domain.repository.ImageRepository
import com.focos_dengue.domain.repository.ReportRepository
import com.focos_dengue.domain.service.ImageCompService
import java.io.File

class SubmitReportUseCase(
    private val reportRepository: ReportRepository,
    private val imageRepository: ImageRepository,
    private val imageCompService: ImageCompService
) {
    suspend operator fun invoke(report: Report): Result<Unit> {
        var publicUrl: String
        var compressedImage: File? = null
        try {
            compressedImage = imageCompService.compressToAvif(report.imageUri)
            publicUrl = imageRepository.uploadImage(compressedImage.toUri())
        } catch (_: Exception) {
            return Result.failure(Throwable("Erro ao enviar denúncia"))
        } finally {
            if (compressedImage != null && compressedImage.exists())
                compressedImage.delete()
        }

        return reportRepository.submitReport(report.copy(imageUri = publicUrl.toUri()))
    }
}