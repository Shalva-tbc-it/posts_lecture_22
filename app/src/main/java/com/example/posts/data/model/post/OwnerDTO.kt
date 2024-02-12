package com.example.posts.data.model.post

import com.squareup.moshi.Json

data class OwnerDTO(
    @Json(name = "first_name")
    val firstName: String,
    @Json(name = "last_name")
    val lastName: String,
    @Json(name = "profile")
    val profile: String?,
    @Json(name = "post_date")
    val postDate: Long
)