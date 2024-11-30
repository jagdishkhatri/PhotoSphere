package com.site2apps.photosphere.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class PixabayResponse(
    val total: Int,
    val totalHits: Int,
    val hits: List<ImageResult>  // List of image results
)

@Parcelize
data class ImageResult(
    val user: String?,
    val tags: String?,
    val views: Int?,
    val likes: Int?,
    val comments: Int?,
    val favorites: Int?,
    val downloads: Int?,
    val imageSize: Int?,
    val type: String?,
    val previewURL: String?,
    val largeImageURL: String?,
    val fullHDURL: String?,
    val imageWidth: Int?,
    val imageHeight: Int?
): Parcelable
