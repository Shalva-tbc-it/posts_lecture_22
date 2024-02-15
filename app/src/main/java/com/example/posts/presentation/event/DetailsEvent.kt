package com.example.posts.presentation.event

sealed class DetailsEvent {
    data class GetDetailPostData(val itemId: Int): DetailsEvent()
    data object ResetErrorMessage : DetailsEvent()
}