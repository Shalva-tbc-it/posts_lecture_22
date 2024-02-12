package com.example.posts.domain.repository

import com.example.posts.data.common.Resource
import com.example.posts.domain.model.GetStories
import kotlinx.coroutines.flow.Flow

interface StoriesRepository {

    suspend fun getStories(): Flow<Resource<List<GetStories>>>

}