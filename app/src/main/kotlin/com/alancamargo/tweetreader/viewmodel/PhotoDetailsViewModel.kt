package com.alancamargo.tweetreader.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alancamargo.tweetreader.handlers.ImageHandler
import com.github.chrisbanes.photoview.PhotoView
import kotlinx.coroutines.launch

class PhotoDetailsViewModel(private val imageHandler: ImageHandler) : ViewModel() {

    fun loadPhoto(photoUrl: String, photoView: PhotoView) {
        viewModelScope.launch {
            imageHandler.loadImage(photoUrl, photoView)
        }
    }

}