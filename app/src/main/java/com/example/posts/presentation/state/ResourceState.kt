package com.example.posts.presentation.state

import com.example.posts.presentation.model.post.Posts

data class ResourceState<T>(
    val isLoading: Boolean = false,
    val data: List<T>? = null,
    val errorMessage: String? = null
)

data class DetailsState<Posts>(
    val isLoading: Boolean = false,
    val data: Posts? = null,
    val errorMessage: String? = null
)
