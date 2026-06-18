package com.focos_dengue.domain.usecase

import androidx.core.net.toUri
import com.focos_dengue.domain.model.ReportModel
import com.focos_dengue.domain.repository.ImageRepository
import com.focos_dengue.domain.repository.ReportRepository
import com.focos_dengue.domain.service.ImageCompService
import java.io.File
import kotlin.time.ExperimentalTime

class SubmitReportUseCase(
    private val reportRepository: ReportRepository,
    private val imageRepository: ImageRepository,
    private val imageCompService: ImageCompService
) {
    @OptIn(ExperimentalTime::class)
    suspend operator fun invoke(report: ReportModel): Result<Unit> {
        var publicUrl: String
        var compressedImage: File? = null
        try {
            compressedImage = imageCompService.compressToAvif(report.imageUri)
            publicUrl = imageRepository.uploadImage(compressedImage.toUri())
        }
        catch (_: Exception) {
            return Result.failure(Throwable("Erro ao enviar denúncia"))
        }
        finally {
            if (compressedImage != null && compressedImage.exists())
                compressedImage.delete()
        }

        val result = reportRepository.submitReport(report.copy(imageUri = publicUrl.toUri()))

        if (result.isSuccess)
            return Result.success(Unit)
        imageRepository.deleteImage(publicUrl)
        return Result.failure(Throwable("Erro ao enviar denúncia"))
    }
}