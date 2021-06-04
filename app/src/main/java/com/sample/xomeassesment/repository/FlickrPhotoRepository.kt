package com.sample.xomeassesment.repository

import com.sample.xomeassesment.model.FlickrPhotoResponse
import com.sample.xomeassesment.network.APIServices

class FlickrPhotoRepository {

    suspend fun getSearchImages(name: String): FlickrPhotoResponse {
        return APIServices.create().getSearchImages(
            "flickr.photos.search",
            "3e7cc266ae2b0e0d78e279ce8e361736", "json",
            "1", "1", name
        )
    }
}
