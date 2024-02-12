package com.example.posts.data.mapper.posts

import com.example.posts.data.model.post.OwnerDTO
import com.example.posts.data.model.post.PostsDTO
import com.example.posts.domain.model.post.GetOwner
import com.example.posts.domain.model.post.GetPosts

fun PostsDTO.toDomain() =
    GetPosts(
        id = id,
        comments = comments,
        images = images,
        likes = likes,
        owner = owner.toDomain(),
        shareContent = shareContent,
        title = title

    )

fun OwnerDTO.toDomain() =
    GetOwner(
        firstName = firstName,
        lastName = lastName,
        profile = profile,
        postDate = postDate
    )