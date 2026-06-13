package com.focos_dengue.data.repository

import com.focos_dengue.data.remote.supabase
import com.focos_dengue.domain.model.Report
import com.focos_dengue.domain.repository.ReportRepository
import io.github.jan.supabase.postgrest.from

class ReportRepositoryImpl : ReportRepository {

    override suspend fun submitReport(report: Report): Result<Unit> {

        return try {

            supabase.from("reports").insert(report)

            Result.success(Unit)

        } catch (e: Exception) {

            Result.failure(e)

        }
    }
}