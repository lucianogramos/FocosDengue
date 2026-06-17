package com.focos_dengue.data.repository

import com.focos_dengue.data.mapper.toDto
import com.focos_dengue.data.remote.ReportDataSource
import com.focos_dengue.domain.model.Report
import com.focos_dengue.domain.repository.ReportRepository

class ReportRepositoryImpl(private val reportDataSource: ReportDataSource) : ReportRepository {

    override suspend fun submitReport(report: Report): Result<Unit> {

        return try {

            reportDataSource.insertReport(report.toDto())

            Result.success(Unit)

        } catch (e: Exception) {

            Result.failure(e)

        }
    }

    override suspend fun getReportHistory(): Result<List<Report>> {
        TODO("Not yet implemented")
    }
}