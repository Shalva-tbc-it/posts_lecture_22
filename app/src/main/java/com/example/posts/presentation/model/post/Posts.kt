package com.example.posts.presentation.model.post

data class Posts(
    val id: Int,
    val comments: Int,
    val images: List<String>?,
    val likes: Int,
    val owner: Owner,
    val shareContent: String,
    val title: String
)
