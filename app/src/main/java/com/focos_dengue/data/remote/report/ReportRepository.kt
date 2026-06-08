package com.focos_dengue.data.remote.report
import com.focos_dengue.data.remote.supabase
import io.github.jan.supabase.postgrest.from

class ReportRepository {

//    suspend fun saveReport(report: Report): Result<Unit> {
//
//        if (report.location.isBlank()) {
//            return Result.failure(Exception("Informe a localização!"))
//        }
//        if (report.levelOfUrgency.isBlank()) {
//            return Result.failure(Exception("Informe o nível de urgência!"))
//        }
//        if (report.category.isBlank()) {
//            return Result.failure(Exception("Informe a categoria!"))
//        }
//
//        return try {
//
//            supabase.from("reports").insert(report)
//
//            Result.success(Unit)
//
//        } catch (e: Exception) {
//
//            Result.failure(e)
//
//        }
//    }
}