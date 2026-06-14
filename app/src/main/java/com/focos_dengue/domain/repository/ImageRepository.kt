package com.focos_dengue.domain.repository

interface ImageRepository {
    suspend fun uploadImage(localPath: String): String
}