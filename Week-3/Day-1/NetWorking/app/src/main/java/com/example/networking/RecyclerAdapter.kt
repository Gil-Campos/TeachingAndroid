package com.example.networking

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.networking.databinding.PostItemLayoutBinding

class RecyclerAdapter(private val posts: List<PostStructure>) :
    RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    class ViewHolder(private val binding: PostItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: PostStructure) {
            binding.tvBody.text = item.body
            binding.userId.text = item.title
            binding.userId.text = item.id.toString()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            PostItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return posts.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(posts[position])
    }
}