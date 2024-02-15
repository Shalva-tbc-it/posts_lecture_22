package com.example.posts.presentation.screen.details

import android.util.Log.d
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.example.posts.databinding.FragmentDetailBinding
import com.example.posts.presentation.common.base.BaseFragment
import com.example.posts.presentation.event.DetailsEvent
import com.example.posts.presentation.event.HomeEvent
import com.example.posts.presentation.extension.loadImage
import com.example.posts.presentation.extension.showSnackBar
import com.example.posts.presentation.model.post.Posts
import com.example.posts.presentation.state.DetailsState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding>(FragmentDetailBinding::inflate) {

    private val viewModel: DetailsViewModel by viewModels()
    private val args : DetailFragmentArgs by navArgs()

    override fun bind() {
        bindEvent()
    }

    override fun bindViewActionListener() {

    }

    override fun bindObserves() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.postsState.collect {
                    handlePosts(it)
                }
            }
        }
    }

    private fun handlePosts(state: DetailsState<Posts>) {
        binding.progress.visibility =
            if (state.isLoading) View.VISIBLE else View.GONE

        state.data?.let {
            setDate(it)
        }

        state.errorMessage?.let {
            binding.root.showSnackBar(message = it)
            d("getCurrentPost", it)
            HomeEvent.ResetErrorMessage
        }

    }

    private fun setDate(posts: Posts) = with(binding) {

        posts.owner.profile?.let {
            imgOwner.visibility = View.VISIBLE
            imgOwner.loadImage(it)
        }
        tvCommentsCount.text = posts.comments.toString()
        tvLikesCount.text = posts.likes.toString()
        tvDesc.text = posts.title
        tvOwnerFullName.text = posts.owner.firstName.plus(" ${posts.owner.lastName}")
        tvPostDate.text = posts.owner.postDate
        posts.images?.let {
            imgMainCover.loadImage(it[0])
        }



    }

    private fun bindEvent() {
        viewModel.onEvent(DetailsEvent.GetDetailPostData(args.itemId))
    }

}