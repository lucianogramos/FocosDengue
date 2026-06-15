package com.focos_dengue.domain.service

interface ImageCompService {

    suspend fun compressToAvif(
        localPath: String
    ): String
}