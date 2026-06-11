package backend.domain.usecase

import backend.domain.model.Location
import backend.domain.model.Report
import backend.domain.model.ReportType
import backend.domain.repository.ReportRepository
import backend.domain.repository.ImageRepository

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
        val imageUrls = imageResult.getOrElse { emptyList() }

        val report = Report(
            type = type,
            description = description,
            location = location.copy(address = location.address ?: location.address),
            imageUrls = imageUrls
        )

        return repository.submitReport(report)
    }
}