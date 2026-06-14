package com.focos_dengue.data.remote

import com.focos_dengue.data.remote.dto.ReportDto
import io.github.jan.supabase.postgrest.Postgrest

class ReportDataSource(
    private val postgrest: Postgrest
) {
    suspend fun insertReport(reportDto: ReportDto) {
        postgrest.from("reports").insert(reportDto)
    }
}