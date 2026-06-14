package com.focos_dengue.domain.repository

interface ImageRepository {
    suspend fun uploadImages(localPaths: List<String>): Result<List<String>>
}