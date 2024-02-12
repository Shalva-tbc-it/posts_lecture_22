package com.example.posts.domain.usecase

import com.example.posts.domain.repository.StoriesRepository
import javax.inject.Inject

class GetStoriesUseCase @Inject constructor(
    private val storiesRepository: StoriesRepository
) {
    suspend operator fun invoke() = storiesRepository.getStories()
}