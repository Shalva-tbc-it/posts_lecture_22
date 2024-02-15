package com.example.posts.data.service

import com.example.posts.data.model.post.PostsDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface DetailsService {

    @GET("d02b76bb-095d-45fa-90e1-dc4733d1f247/{id}")
    suspend fun getDetails(@Path("id") id: Int): Response<PostsDTO>

}