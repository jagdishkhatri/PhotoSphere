package com.site2apps.photosphere.data.repository

import com.site2apps.photosphere.data.api.ApiService
import com.site2apps.photosphere.data.model.ImageResult

class ImageRepository(private val apiService: ApiService) {
    suspend fun getImages(
        apiKey: String,
        query: String,
        page: Int,
        perPage: Int
    ): List<ImageResult> {
        val response = apiService.getImages(apiKey, query, page, perPage)
        return response.hits  // Return the list of images
    }
}