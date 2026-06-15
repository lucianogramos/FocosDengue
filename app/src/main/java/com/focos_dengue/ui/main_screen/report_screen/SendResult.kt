package com.focos_dengue.ui.main_screen.report_screen

sealed class SendResult {
    object Success : SendResult()
    data class Error(val message: String) : SendResult()
}