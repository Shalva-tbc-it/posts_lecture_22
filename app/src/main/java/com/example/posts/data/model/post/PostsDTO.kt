package com.example.posts.data.model.post

import com.squareup.moshi.Json

data class PostsDTO(
    @Json(name = "id")
    val id: Int,
    @Json(name = "comments")
    val comments: Int,
    @Json(name = "images")
    val images: List<String>?,
    @Json(name = "likes")
    val likes: Int,
    @Json(name = "owner")
    val owner: OwnerDTO,
    @Json(name = "share_content")
    val shareContent: String,
    @Json(name = "title")
    val title: String
)