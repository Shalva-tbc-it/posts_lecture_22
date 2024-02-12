package com.example.posts.presentation.screen.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.posts.databinding.RecyclerPostsBinding
import com.example.posts.presentation.extension.loadImage
import com.example.posts.presentation.model.post.Posts
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class PostsRecyclerAdapter : ListAdapter<Posts, PostsRecyclerAdapter.PostsViewHolder>(PostsDiffUtil()) {
    inner class PostsViewHolder(private val binding: RecyclerPostsBinding) : RecyclerView.ViewHolder(binding.root)  {
        fun bind() = with(binding) {
            val item = currentList[adapterPosition]

            "${item.owner.firstName}  ${item.owner.lastName}".also { tvOwnerFullName.text = it }
            imgOwner.loadImage(item.owner.profile)
            tvPostDate.text = convertToDate(item.owner.postDate)
            tvDesc.text = item.shareContent
            imgMainCover.loadImage(getImageAtIndex(item, 0))
            imgTopCover.loadImage(getImageAtIndex(item, 1))
            imgBottomCover.loadImage(getImageAtIndex(item, 2))



        }
        fun getImageAtIndex(item: Posts, index: Int): String? {
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
                LayoutInflater.from(parent.context),
                parent,
                false
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