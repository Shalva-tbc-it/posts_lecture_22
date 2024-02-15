package com.example.posts.presentation.screen.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.posts.data.common.Resource
import com.example.posts.domain.usecase.GetDetailsUseCase
import com.example.posts.presentation.event.DetailsEvent
import com.example.posts.presentation.mapper.post.toPresentation
import com.example.posts.presentation.model.post.Posts
import com.example.posts.presentation.state.DetailsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val detailsUawCase: GetDetailsUseCase
): ViewModel() {


    private val _postsState = MutableStateFlow(DetailsState<Posts>())
    val postsState: SharedFlow<DetailsState<Posts>> = _postsState.asStateFlow()


    fun onEvent(event: DetailsEvent) {
        when (event) {
            is DetailsEvent.GetDetailPostData -> getDetailPost(event.itemId)
            is DetailsEvent.ResetErrorMessage -> updateErrorMessage(message = null)
        }
    }


    private fun getDetailPost(itemId: Int) {
        viewModelScope.launch {
            detailsUawCase.invoke(itemId).collect { resource ->
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
                            state.copy(
                                data = resource.data.toPresentation()
                            )
                        }
                    }

                    is Resource.Error -> updateErrorMessage(message = resource.errorMessage)
                }
            }
        }
    }

    private fun updateErrorMessage(message: String?) {
        _postsState.update { it.copy(errorMessage = message) }
    }

}