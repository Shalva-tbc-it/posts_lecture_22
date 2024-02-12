package com.example.posts.domain.model

import com.squareup.moshi.Json

data class GetStories(
    val id: Int,
    val cover: String,
    val title: String,
)