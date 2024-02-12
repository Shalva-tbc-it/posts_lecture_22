package com.example.posts.presentation.event

sealed class HomeEvent {
    data object GetStoriesData: HomeEvent()
    data object GetPostsData: HomeEvent()
    data object ResetErrorMessage : HomeEvent()

}