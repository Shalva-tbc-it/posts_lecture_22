package com.example.posts.domain.usecase

import com.example.posts.domain.repository.PostsRepository
import javax.inject.Inject

class GetPostsUseCase @Inject constructor(
    private val postsRepository: PostsRepository
) {
    suspend operator fun invoke() = postsRepository.getPosts()
}