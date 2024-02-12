package com.example.posts.presentation.mapper

import com.example.posts.domain.model.GetStories
import com.example.posts.presentation.model.Stories


fun GetStories.toPresentation() =
    Stories(
        id = id,
        cover = cover,
        title = title

    )