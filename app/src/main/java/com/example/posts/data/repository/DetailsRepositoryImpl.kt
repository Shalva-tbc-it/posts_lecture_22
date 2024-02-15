package com.example.posts.data.repository

import com.example.posts.data.common.HandleResponse
import com.example.posts.data.common.Resource
import com.example.posts.data.mapper.base.asResource
import com.example.posts.data.mapper.posts.toDomain
import com.example.posts.data.service.DetailsService
import com.example.posts.domain.model.post.GetPosts
import com.example.posts.domain.repository.DetailsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DetailsRepositoryImpl @Inject constructor(
    private val detailsService: DetailsService,
    private val handleResponse: HandleResponse
): DetailsRepository {
    override suspend fun getDetails(itemId: Int): Flow<Resource<GetPosts>> {
       return handleResponse.safeApiCall {
            detailsService.getDetails(id = itemId)
        }.asResource {
            it.toDomain()
        }

    }

}