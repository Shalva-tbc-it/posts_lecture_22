package com.example.posts.presentation.state

import com.example.posts.presentation.model.Stories

data class StoriesState (
    val isLoading: Boolean = false,
    val data: List<Stories>? = null,
    val errorMessage: String? = null
)