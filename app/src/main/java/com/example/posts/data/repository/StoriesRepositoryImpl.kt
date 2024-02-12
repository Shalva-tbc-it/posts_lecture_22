package com.example.posts.data.repository

import com.example.posts.data.common.HandleResponse
import com.example.posts.data.common.Resource
import com.example.posts.data.mapper.base.asResource
import com.example.posts.data.mapper.toDomain
import com.example.posts.data.service.StoriesService
import com.example.posts.domain.model.GetStories
import com.example.posts.domain.repository.StoriesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class StoriesRepositoryImpl @Inject constructor(
    private val storiesService: StoriesService,
    private val handleResponse: HandleResponse
): StoriesRepository {
    override suspend fun getStories(): Flow<Resource<List<GetStories>>> {
        return handleResponse.safeApiCall {
            storiesService.getStories()
        }.asResource { list ->
            list.map {
                it.toDomain()
            }
        }
    }
}