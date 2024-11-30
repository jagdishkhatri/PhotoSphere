package com.site2apps.photosphere.data.api

import com.site2apps.photosphere.data.model.PixabayResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("api/")
    suspend fun getImages(
        @Query("key") apiKey: String,
        @Query("q") query: String,  // Search term (e.g., "yellow flowers")
        @Query("page") page: Int,   // Page number for pagination
        @Query("per_page") perPage: Int,  // Results per page
        @Query("lang") lang: String = "en",  // Language, default to English
        @Query("image_type") imageType: String = "photo",  // Default to photo
        @Query("orientation") orientation: String = "all",  // Filter by image orientation
        @Query("category") category: String? = null,  // Optional, filter by category
        @Query("safesearch") safeSearch: Boolean = true  // Filter out unsafe content
    ): PixabayResponse
}