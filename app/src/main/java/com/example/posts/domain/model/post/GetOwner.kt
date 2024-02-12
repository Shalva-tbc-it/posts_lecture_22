package com.example.posts.domain.model.post

data class GetOwner(
    val firstName: String,
    val lastName: String,
    val profile: String?,
    val postDate: Long
)
