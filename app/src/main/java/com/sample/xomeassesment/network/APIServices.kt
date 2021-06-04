package com.sample.xomeassesment.network

import com.sample.xomeassesment.model.FlickrPhotoResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface APIServices {
    @Headers("Accept: application/json")
    @GET("rest?")
    suspend fun getSearchImages(
        @Query("method") method: String,
        @Query("api_key") api_key: String,
        @Query("format") format: String,
        @Query("nojsoncallback") nojsoncallback: String,
        @Query("safe_search") safe_search: String,
        @Query("text") text: String
    ): FlickrPhotoResponse

    companion object {

        var BASE_URL = "https://api.flickr.com/services/"

        fun create(): APIServices {

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(APIServices::class.java)
        }
    }
}
