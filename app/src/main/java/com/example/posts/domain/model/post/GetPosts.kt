package com.example.posts.domain.model.post

data class GetPosts(
    val id: Int,
    val comments: Int,
    val images: List<String>?,
    val likes: Int,
    val owner: GetOwner,
    val shareContent: String,
    val title: String
)
