package com.focos_dengue.ui.main_screen.report_screen

sealed class SendResult {
    object Sucess : SendResult()
    data class Error(val message: String) : SendResult()
}