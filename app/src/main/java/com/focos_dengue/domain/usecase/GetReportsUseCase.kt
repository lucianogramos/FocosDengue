package com.focos_dengue.domain.usecase

import com.focos_dengue.domain.model.ReportModel
import com.focos_dengue.domain.repository.GeoLocationRepository
import com.focos_dengue.domain.repository.ReportRepository
import kotlin.time.ExperimentalTime

class GetReportsUseCase(
    private val repository: ReportRepository,
    private val geoLocationRepository: GeoLocationRepository
) {

    @OptIn(ExperimentalTime::class)
    suspend operator fun invoke(from: Long, limit: Long): List<ReportModel> {
        val result = repository.getReports(from, limit)

        if (result.isFailure) {
            return emptyList()
        }

        val reports = result.getOrDefault(emptyList())

        return reports.map { report ->
            val address = geoLocationRepository.getAdress(
                lat = report.location.latitude,
                lng = report.location.longitude
            )
            report.copy(location = report.location.copy(address = address))
        }
    }
}