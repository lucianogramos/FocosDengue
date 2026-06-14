package com.focos_dengue.data.remote

import com.focos_dengue.data.remote.dto.ReportDto
import io.github.jan.supabase.postgrest.from

class ReportDataSource {
    suspend fun insertReport(reportDto: ReportDto) {
        supabase.from("reports").insert(reportDto)
    }
}