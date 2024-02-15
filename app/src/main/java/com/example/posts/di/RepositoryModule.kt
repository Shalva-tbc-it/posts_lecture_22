package com.example.posts.di

import com.example.posts.data.common.HandleResponse
import com.example.posts.data.repository.DetailsRepositoryImpl
import com.example.posts.data.repository.PostsRepositoryImpl
import com.example.posts.data.repository.StoriesRepositoryImpl
import com.example.posts.data.service.DetailsService
import com.example.posts.data.service.PostsService
import com.example.posts.data.service.StoriesService
import com.example.posts.domain.repository.DetailsRepository
import com.example.posts.domain.repository.PostsRepository
import com.example.posts.domain.repository.StoriesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideStoriesRepository(
        storiesService: StoriesService,
        handleResponse: HandleResponse
    ): StoriesRepository{
        return StoriesRepositoryImpl(
            storiesService = storiesService,
            handleResponse = handleResponse
        )
    }

    @Provides
    @Singleton
    fun providePostsRepository(
        postsService: PostsService,
        handleResponse: HandleResponse
    ): PostsRepository {
        return PostsRepositoryImpl(
            postsService = postsService,
            handleResponse = handleResponse
        )
    }

    @Provides
    @Singleton
    fun provideDetailsRepository(
        detailsService: DetailsService,
        handleResponse: HandleResponse
    ): DetailsRepository {
        return DetailsRepositoryImpl(
            detailsService = detailsService,
            handleResponse = handleResponse
        )
    }


}