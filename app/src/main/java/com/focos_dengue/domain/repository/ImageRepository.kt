package com.focos_dengue.domain.repository

import android.net.Uri

interface ImageRepository {
    suspend fun uploadImage(uri: Uri): String
}