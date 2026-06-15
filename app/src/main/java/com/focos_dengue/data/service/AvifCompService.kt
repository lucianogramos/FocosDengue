package com.focos_dengue.data.service

import com.focos_dengue.domain.service.ImageCompService

class AvifCompressionService : ImageCompService {

    override suspend fun compressToAvif(
        localPath: String
    ): String {

        return localPath
    }
}