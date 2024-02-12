package com.example.posts.presentation.state

import com.example.posts.presentation.model.post.Posts

data class PostsState(
    val isLoading: Boolean = false,
    val data: List<Posts>? = null,
    val errorMessage: String? = null
)
