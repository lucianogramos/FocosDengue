package backend.domain.repository

import backend.domain.model.Report

interface ReportRepository {
    suspend fun submitReport(report: Report): Result<Report>
}