package com.site2apps.photosphere.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.site2apps.photosphere.data.model.ImageResult
import com.site2apps.photosphere.data.repository.ImageRepository
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: ImageRepository) : ViewModel() {

    private val _imageList = MutableLiveData<List<ImageResult>>()
    val imageList: LiveData<List<ImageResult>> get() = _imageList

    fun fetchImages(apiKey: String, query: String, page: Int, perPage: Int) {
        viewModelScope.launch {
            try {
                val images = repository.getImages(apiKey, query, page, perPage)
                _imageList.postValue(images)
            } catch (e: Exception) {
                Log.e(">>>>>>>>>>.", "fetchImages: $e" )
            }
        }
    }
}
