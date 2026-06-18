package com.focos_dengue.data.remote

import com.focos_dengue.data.remote.dto.ReportDto
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.query.Order

class ReportDataSource(
    private val postgrest: Postgrest
) {
    suspend fun insertReport(reportDto: ReportDto) {
        postgrest.from("reports").insert(reportDto)
    }

    suspend fun getReports(from: Long, limit: Long): List<ReportDto> {
        return postgrest.from("reports").select {
                order("created_at", Order.DESCENDING)
                range(from, from + limit - 1)
            }.decodeList<ReportDto>()
    }
}