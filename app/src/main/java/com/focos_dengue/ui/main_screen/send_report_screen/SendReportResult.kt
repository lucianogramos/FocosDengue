package com.focos_dengue.ui.main_screen.send_report_screen

sealed class SendReportResult {
    object Success : SendReportResult()
    data class Error(val message: String) : SendReportResult()
}