package com.example.posts.data.model

import com.squareup.moshi.Json

data class StoriesDTO(
    @Json(name = "id")
    val id: Int,
    @Json(name = "cover")
    val cover: String,
    @Json(name = "title")
    val title: String,
)
