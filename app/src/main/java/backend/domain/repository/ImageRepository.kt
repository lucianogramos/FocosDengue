package backend.domain.repository

interface ImageRepository {
    suspend fun uploadImages(localPaths: List<String>): Result<List<String>>
}