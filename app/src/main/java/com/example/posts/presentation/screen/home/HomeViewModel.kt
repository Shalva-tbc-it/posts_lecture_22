package com.example.posts.presentation.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.posts.data.common.Resource
import com.example.posts.domain.usecase.GetPostsUseCase
import com.example.posts.domain.usecase.GetStoriesUseCase
import com.example.posts.presentation.event.HomeEvent
import com.example.posts.presentation.mapper.post.toPresentation
import com.example.posts.presentation.mapper.toPresentation
import com.example.posts.presentation.model.Stories
import com.example.posts.presentation.model.post.Posts
import com.example.posts.presentation.state.ResourceState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(

    private val getStoriesUseCase: GetStoriesUseCase, private val getPostsUseCase: GetPostsUseCase

) : ViewModel() {

    private val _storiesState = MutableStateFlow(ResourceState<Stories>())
    val storiesState: SharedFlow<ResourceState<Stories>> = _storiesState.asStateFlow()

    private val _postsState = MutableStateFlow(ResourceState<Posts>())
    val postsState: SharedFlow<ResourceState<Posts>> = _postsState.asStateFlow()


    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.GetStoriesData -> getStories()
            is HomeEvent.GetPostsData -> getPosts()
            is HomeEvent.ResetErrorMessage -> updateErrorMessage(message = null)
        }
    }

    private fun getStories() {
        viewModelScope.launch {
            getStoriesUseCase.invoke().collect { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        _storiesState.update { state ->
                            state.copy(
                                isLoading = resource.loading
                            )
                        }
                    }

                    is Resource.Success -> {
                        _storiesState.update { state ->
                            state.copy(data = resource.data.map {
                                it.toPresentation()
                            })
                        }
                    }

                    is Resource.Error -> updateErrorMessage(message = resource.errorMessage)
                }
            }
        }
    }

    private fun getPosts() {
        viewModelScope.launch {
            getPostsUseCase.invoke().collect { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        _postsState.update { state ->
                            state.copy(
                                isLoading = resource.loading
                            )
                        }
                    }

                    is Resource.Success -> {
                        _postsState.update { state ->
                            state.copy(data = resource.data.map {
                                it.toPresentation()
                            })
                        }
                    }

                    is Resource.Error -> updateErrorMessage(message = resource.errorMessage)
                }
            }
        }
    }

    private fun updateErrorMessage(message: String?) {
        _storiesState.update { it.copy(errorMessage = message) }
    }

}