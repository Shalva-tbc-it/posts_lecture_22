package com.example.posts.data.service

import com.example.posts.data.model.StoriesDTO
import retrofit2.Response
import retrofit2.http.GET

interface StoriesService {

    @GET("1e2c42be-fc82-4efb-9f3f-4ce4ce80743c")
    suspend fun getStories() : Response<List<StoriesDTO>>

}