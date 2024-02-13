package com.example.posts.presentation.screen.home

import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.posts.databinding.FragmentHomeBinding
import com.example.posts.presentation.common.base.BaseFragment
import com.example.posts.presentation.event.HomeEvent
import com.example.posts.presentation.extension.showSnackBar
import com.example.posts.presentation.model.Stories
import com.example.posts.presentation.model.post.Posts
import com.example.posts.presentation.screen.home.adapter.PostsRecyclerAdapter
import com.example.posts.presentation.screen.home.adapter.StoriesRecyclerAdapter
import com.example.posts.presentation.state.ResourceState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val viewModel: HomeViewModel by viewModels()
    private lateinit var storiesAdapter: StoriesRecyclerAdapter
    private lateinit var postsAdapter: PostsRecyclerAdapter

    override fun bind() {
        bindAdapter()
        events()
    }

    override fun bindViewActionListener() {

    }

    override fun bindObserves() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.storiesState.collect {
                    handleStories(state = it)
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.postsState.collect {
                    handlePosts(it)
                }
            }
        }

    }

    private fun events() {
        viewModel.onEvent(HomeEvent.GetStoriesData)
        viewModel.onEvent(HomeEvent.GetPostsData)

    }


    private fun bindAdapter() {
        storiesAdapter = StoriesRecyclerAdapter()
        binding.recyclerStories.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerStories.adapter = storiesAdapter

        postsAdapter = PostsRecyclerAdapter()
        binding.recyclerPosts.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerPosts.adapter = postsAdapter
    }

    private fun handlePosts(state: ResourceState<Posts>) {
        binding.progressBar.visibility =
            if (state.isLoading) View.VISIBLE else View.GONE

        state.data?.let {
            postsAdapter.submitList(it)
        }

        state.errorMessage?.let {
            binding.root.showSnackBar(message = it)
            HomeEvent.ResetErrorMessage
        }

    }

    private fun handleStories(state: ResourceState<Stories>) {
        binding.progressBar.visibility =
            if (state.isLoading) View.VISIBLE else View.GONE

        state.data?.let {
            storiesAdapter.submitList(it)
        }

        state.errorMessage?.let {
            binding.root.showSnackBar(message = it)
            HomeEvent.ResetErrorMessage
        }

    }

}