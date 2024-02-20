package com.example.posts.presentation.screen.details

import com.example.posts.data.common.Resource
import com.example.posts.domain.model.post.GetOwner
import com.example.posts.domain.model.post.GetPosts
import com.example.posts.domain.usecase.GetDetailsUseCase
import com.example.posts.presentation.event.DetailsEvent
import com.example.posts.presentation.state.DetailsState
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.newSingleThreadContext
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class DetailsViewModelTest {
    private lateinit var detailsUseCase: GetDetailsUseCase
    private lateinit var viewModel: DetailsViewModel
    @OptIn(ExperimentalCoroutinesApi::class, DelicateCoroutinesApi::class)
    private val testDispatcher = newSingleThreadContext(name = "UI thread")

    @Before
    fun setup() {
        detailsUseCase = mockk()
        viewModel = DetailsViewModel(detailsUseCase)
    }

    @Test
    fun `test success path`() = testDispatcher.dispatch(Dispatchers.IO) {
        // Arrange
        val itemId = 1
        val expectedResult = GetPosts(
            id = 8396, comments = 5497, images = listOf(), likes = 7576, owner = GetOwner(
                firstName = "Adele",
                lastName = "Elwood",
                profile = null,
                postDate = 1001L
            ), shareContent = "Tarrance", title = "Josue"

        )
        coEvery { detailsUseCase.invoke(itemId) } returns flowOf(Resource.Success(expectedResult))

        // Act
        viewModel.onEvent(DetailsEvent.GetDetailPostData(itemId))

        // Assert
        val expectedState = DetailsState<GetPosts>(data = expectedResult)
        assertEquals(expectedState, viewModel.postsState.replayCache.first())
    }

    @Test
    fun `test error path`() = testDispatcher.dispatch(Dispatchers.IO) {
        // Arrange
        val itemId = 1
        val expectedError = "Some error message"
        coEvery { detailsUseCase.invoke(itemId) } returns flowOf(Resource.Error(expectedError))

        // Act
        viewModel.onEvent(DetailsEvent.GetDetailPostData(itemId))

        // Assert
        val expectedState = DetailsState<GetPosts>(errorMessage = expectedError)
        assertEquals(expectedState, viewModel.postsState.replayCache.first())
    }

}


