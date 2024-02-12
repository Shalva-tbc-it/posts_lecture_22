package com.example.posts.data.mapper

import com.example.posts.data.model.StoriesDTO
import com.example.posts.domain.model.GetStories


fun StoriesDTO.toDomain() =
    GetStories(
        id = id,
        cover = cover,
        title = title

    )