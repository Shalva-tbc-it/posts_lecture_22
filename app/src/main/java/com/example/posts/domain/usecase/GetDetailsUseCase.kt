package com.example.posts.domain.usecase

import com.example.posts.data.common.Resource
import com.example.posts.domain.model.post.GetPosts
import com.example.posts.domain.repository.DetailsRepository
import com.example.posts.domain.usecase_interface.DetailsUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetDetailsUseCase @Inject constructor(
    private val detailsRepository: DetailsRepository
) : DetailsUseCase {
    override suspend operator fun invoke(itemId: Int): Flow<Resource<GetPosts>> {
        return detailsRepository.getDetails(itemId)
    }
}