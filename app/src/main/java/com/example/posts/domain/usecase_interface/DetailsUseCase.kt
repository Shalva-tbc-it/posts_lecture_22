package com.example.posts.domain.usecase_interface

import com.example.posts.data.common.Resource
import com.example.posts.domain.model.post.GetPosts
import kotlinx.coroutines.flow.Flow

interface DetailsUseCase {
    suspend operator fun invoke(itemId: Int): Flow<Resource<GetPosts>>
}