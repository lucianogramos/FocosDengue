package com.focos_dengue.data.service

import com.alfikri.rizky.avifkit.AvifConverter
import com.alfikri.rizky.avifkit.ImageInput
import com.alfikri.rizky.avifkit.Priority
import com.focos_dengue.domain.service.ImageCompService
import java.io.File

class AvifCompService : ImageCompService {

    override suspend fun compressToAvif(
        localPath: String
    ): String {

        val inputFile = File(localPath)

        val outputPath = File(
            inputFile.parent,
            "${inputFile.nameWithoutExtension}.avif"
        ).absolutePath

        val converter = AvifConverter()

        converter.convertToFile(
            input = ImageInput.from(localPath),
            outputPath = outputPath,
            priority = Priority.BALANCED
        )

        return outputPath
    }
}