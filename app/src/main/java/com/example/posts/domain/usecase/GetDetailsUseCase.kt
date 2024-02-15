package com.example.posts.domain.usecase

import com.example.posts.domain.repository.DetailsRepository
import javax.inject.Inject

class GetDetailsUseCase @Inject constructor(
    private val detailsRepository: DetailsRepository
) {
    suspend operator fun invoke(itemId: Int) = detailsRepository.getDetails(itemId)
}