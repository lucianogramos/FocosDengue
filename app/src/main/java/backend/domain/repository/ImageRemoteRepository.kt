package backend.domain.repository

import backend.domain.repository.ImageRepository

class ImageRemoteRepository : ImageRepository {
    override suspend fun uploadImages(localPaths: List<String>): Result<List<String>> {
        // Simulação: retornar listas com "https://storage/.../filename.jpg"
        val urls = localPaths.map { path ->
            "https://storage.example.com/${path.substringAfterLast('/')}"
        }
        return Result.success(urls)
    }
}