package com.example.posts.data.repository

import com.example.posts.data.common.HandleResponse
import com.example.posts.data.common.Resource
import com.example.posts.data.mapper.base.asResource
import com.example.posts.data.mapper.posts.toDomain
import com.example.posts.data.service.PostsService
import com.example.posts.domain.model.post.GetPosts
import com.example.posts.domain.repository.PostsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PostsRepositoryImpl @Inject constructor(
    private val postsService: PostsService,
    private val handleResponse: HandleResponse
): PostsRepository {
    override suspend fun getPosts(): Flow<Resource<List<GetPosts>>>  {
        return handleResponse.safeApiCall {
            postsService.getPosts()
        }.asResource { list ->
            list.map {
                it.toDomain()
            }
        }
    }


}