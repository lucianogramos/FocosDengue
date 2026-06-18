package com.focos_dengue.data.remote

import android.content.Context
import android.net.Uri
import io.github.jan.supabase.storage.Storage
import java.util.UUID

class ImageDataSource(
    private val storage: Storage,
    private val context: Context
) {

    suspend fun uploadImage(uri: Uri): String {
        val bytes = context.contentResolver
            .openInputStream(uri)
            ?.readBytes()
            ?: throw Exception("Não foi possível ler a imagem")

        val fileName = "${UUID.randomUUID()}.avif"

        val bucket = storage.from("reports")

        bucket.upload(
            path = fileName,
            data = bytes
        )

        return bucket.publicUrl(fileName)
    }

    suspend fun deleteImage(imageUrl: String) {

        val fileName = imageUrl.substringAfterLast("/")

        storage.from("reports").delete(listOf(fileName))
    }
}