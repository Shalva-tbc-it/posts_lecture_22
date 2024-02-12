package com.example.posts.presentation.screen.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.posts.databinding.RecyclerStoriesBinding
import com.example.posts.presentation.extension.loadImage
import com.example.posts.presentation.model.Stories

class StoriesRecyclerAdapter : ListAdapter<Stories, StoriesRecyclerAdapter.StoriesViewHolder>(StoriesDiffUtil()) {

    inner class StoriesViewHolder(private val binding: RecyclerStoriesBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind() = with(binding) {

            val item = currentList[adapterPosition]

            imgCover.loadImage(item.cover)
            tvTitle.text = item.title

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoriesViewHolder {
        return StoriesViewHolder(
            RecyclerStoriesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: StoriesViewHolder, position: Int) {
        holder.bind()
    }

    class StoriesDiffUtil : DiffUtil.ItemCallback<Stories>() {
        override fun areItemsTheSame(oldItem: Stories, newItem: Stories): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Stories, newItem: Stories): Boolean {
            return oldItem == newItem
        }

    }

}