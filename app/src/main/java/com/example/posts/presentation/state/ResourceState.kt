package com.example.posts.presentation.state

data class ResourceState<T>(
    val isLoading: Boolean = false,
    val data: List<T>? = null,
    val errorMessage: String? = null
)
