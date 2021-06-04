package com.sample.xomeassesment.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.sample.xomeassesment.model.FlickrPhotoResponse
import com.sample.xomeassesment.repository.FlickrPhotoRepository

class FlickrPhotoViewModel : ViewModel() {

    var flikrRepository: FlickrPhotoRepository = FlickrPhotoRepository()

    fun getSearchImages(name: String) = liveData {
        try {
            val response = flikrRepository.getSearchImages(name)
            emit(Result.success(response))
        } catch (cause: Throwable) {
            emit(Result.failure<FlickrPhotoResponse>(cause))
        }
    }
}
