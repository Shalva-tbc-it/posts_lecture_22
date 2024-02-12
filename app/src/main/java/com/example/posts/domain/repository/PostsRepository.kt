package com.example.posts.domain.repository

import com.example.posts.data.common.Resource
import com.example.posts.domain.model.post.GetPosts
import kotlinx.coroutines.flow.Flow

interface PostsRepository {
    suspend fun getPosts(): Flow<Resource<List<GetPosts>>>
}