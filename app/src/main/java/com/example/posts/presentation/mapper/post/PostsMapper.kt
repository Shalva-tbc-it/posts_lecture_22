package com.example.posts.presentation.mapper.post

import com.example.posts.domain.model.post.GetOwner
import com.example.posts.domain.model.post.GetPosts
import com.example.posts.presentation.model.post.Owner
import com.example.posts.presentation.model.post.Posts

fun GetPosts.toPresentation() =
    Posts(
        id = id,
        comments = comments,
        images = images,
        likes = likes,
        owner = owner.toPresentation(),
        shareContent = shareContent,
        title = title

    )

fun GetOwner.toPresentation() =
    Owner(
        firstName = firstName,
        lastName = lastName,
        profile = profile,
        postDate = postDate

    )