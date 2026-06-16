package com.focos_dengue.domain.service

import android.content.Context
import android.net.Uri
import com.alfikri.rizky.avifkit.AvifConverter
import com.alfikri.rizky.avifkit.EncodingOptions
import com.alfikri.rizky.avifkit.ImageInput
import com.alfikri.rizky.avifkit.Priority
import java.io.File
import java.io.FileOutputStream

class ImageCompService(private val context: Context) {
    suspend fun compressToAvif(photoUri: Uri, maxSize: Long = 100 * 1024): File {
        val tempInputFile = File(context.cacheDir, "temp_input_file_${System.currentTimeMillis()}.jpg")

        context.contentResolver.openInputStream(photoUri)?.use { inputStream ->
            FileOutputStream(tempInputFile).use { outputStream ->
                inputStream.copyTo(outputStream)
            }
        } ?: throw IllegalArgumentException("Não foi possível ler os dados da URI fornecida")

        val outputFile = File(
            context.cacheDir,
            "compressed_image_${System.currentTimeMillis()}.avif"
        )

        val converter = AvifConverter()
        try {
            converter.convertToFile(
                input = ImageInput.from(tempInputFile.absolutePath),
                outputPath = outputFile.absolutePath,
                priority = Priority.BALANCED,
                options = EncodingOptions(maxSize = maxSize)
            )
        }
        finally {
            if (tempInputFile.exists()) {
                tempInputFile.delete()
            }
        }

        return outputFile
    }
}