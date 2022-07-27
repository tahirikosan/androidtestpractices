package com.tahirikosan.testpractices.data.remote

import com.tahirikosan.testpractices.BuildConfig
import com.tahirikosan.testpractices.data.remote.responses.ImageResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PixabayAPI {

    @GET("/api/")
    suspend fun searchForImages(
        @Query("q") searchQuery:String,
        @Query("key") apiKey:String = BuildConfig.API_KEY,
    ): Response<ImageResponse>

}