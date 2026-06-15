package com.focos_dengue.data.repository

import android.net.Uri
import com.focos_dengue.data.remote.ImageDataSource
import com.focos_dengue.domain.repository.ImageRepository

class ImageRepositoryImpl(
    private val imageDataSource: ImageDataSource
) : ImageRepository {

    override suspend fun uploadImage(uri: Uri): String {
        return imageDataSource.uploadImage(uri)
    }
}