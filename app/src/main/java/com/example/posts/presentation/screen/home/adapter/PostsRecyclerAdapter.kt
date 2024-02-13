package com.example.posts.presentation.screen.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.posts.R
import com.example.posts.databinding.RecyclerPostsBinding
import com.example.posts.presentation.extension.loadImage
import com.example.posts.presentation.extension.setVisibilityAndMargins
import com.example.posts.presentation.model.post.Posts
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class PostsRecyclerAdapter :
    ListAdapter<Posts, PostsRecyclerAdapter.PostsViewHolder>(PostsDiffUtil()) {
    inner class PostsViewHolder(private val binding: RecyclerPostsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() = with(binding) {
            val item = currentList[adapterPosition]

            "${item.owner.firstName}  ${item.owner.lastName}".also { tvOwnerFullName.text = it }
            imgOwner.loadImage(item.owner.profile)
            tvPostDate.text = convertToDate(item.owner.postDate)
            tvDesc.text = item.shareContent
            imgMainCover.loadImage(getImageAtIndex(item, 0))
            imgTopCover.loadImage(getImageAtIndex(item, 1))
            imgBottomCover.loadImage(getImageAtIndex(item, 2))
            tvCommentsCount.text = item.comments.toString()
            tvLikesCount.text = item.likes.toString()
            if (item.owner.profile == null) {
                imgOwner.visibility = View.GONE
            }
            if (imgOwner.isVisible) {
                val params = tvOwnerFullName.layoutParams as ViewGroup.MarginLayoutParams
                val marginDp =
                    binding.root.context.resources.getDimensionPixelSize(R.dimen.margin_15dp)
                params.setMargins(marginDp, 0, 0, 0)
            }

            when (item.images?.size) {

                null -> {
                    imgMainCover.setVisibilityAndMargins(visible = false, 0, 0, 0, 0)
                    imgTopCover.setVisibilityAndMargins(visible = false, 0, 0, 0, 0)
                    imgBottomCover.setVisibilityAndMargins(visible = false, 0, 0, 0, 0)
                }

                1 -> {
                    imgTopCover.setVisibilityAndMargins(visible = false, 0, 0, 0, 0)
                    imgBottomCover.setVisibilityAndMargins(visible = false, 0, 0, 0, 0)
                }

                2 -> {
                    val marginDp =
                        binding.root.context.resources.getDimensionPixelSize(R.dimen.margin_10dp)
                    imgBottomCover.setVisibilityAndMargins(visible = false, 0, 0, 0, 0)
                    imgMainCover.setVisibilityAndMargins(visible = true, 0, 0, marginDp, 0)
                }

                3 -> {
                    val marginDp =
                        binding.root.context.resources.getDimensionPixelSize(R.dimen.margin_10dp)
                    val marginDpTopImg =
                        binding.root.context.resources.getDimensionPixelSize(R.dimen.margin_12dp)

                    imgMainCover.setVisibilityAndMargins(visible = true, 0, 0, marginDp, 0)
                    imgTopCover.setVisibilityAndMargins(visible = true, 0, 0, 0, marginDpTopImg)
                }
            }

        }

        private fun getImageAtIndex(item: Posts, index: Int): String? {
            return item.images?.getOrNull(index)
        }

        private fun convertToDate(timestamp: Long): String {
            val date = Date(timestamp * 1000)
            val getDate = SimpleDateFormat("dd MMMM 'at' h:mm a", Locale.getDefault())
            return getDate.format(date)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsViewHolder {
        return PostsViewHolder(
            RecyclerPostsBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) {
        holder.bind()
    }

    class PostsDiffUtil : DiffUtil.ItemCallback<Posts>() {
        override fun areItemsTheSame(oldItem: Posts, newItem: Posts): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Posts, newItem: Posts): Boolean {
            return oldItem == newItem
        }

    }
}