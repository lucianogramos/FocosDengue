package com.focos_dengue.ui.main_screen.account_screen

sealed class SaveResult {
    object Success : SaveResult()
    data class Error(val message: String) : SaveResult()
}
