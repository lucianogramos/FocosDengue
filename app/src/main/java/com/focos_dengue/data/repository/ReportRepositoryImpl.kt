package com.focos_dengue.data.repository

import com.focos_dengue.data.mapper.toDto
import com.focos_dengue.data.mapper.toModel
import com.focos_dengue.data.remote.ReportDataSource
import com.focos_dengue.domain.model.ReportModel
import com.focos_dengue.domain.repository.ReportRepository

class ReportRepositoryImpl(private val reportDataSource: ReportDataSource) : ReportRepository {

    override suspend fun submitReport(report: ReportModel): Result<Unit> {

        return try {

            reportDataSource.insertReport(report.toDto())

            Result.success(Unit)

        } catch (e: Exception) {

            Result.failure(e)

        }
    }

    override suspend fun getReports(): Result<List<ReportModel>> {

        return try {

            val reports = reportDataSource.getReports().map { it.toModel() }

            Result.success(reports)

        } catch (e: Exception) {

            Result.failure(e)

        }
    }
}