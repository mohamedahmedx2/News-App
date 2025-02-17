package com.example.newsapp.news

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapp.R
import com.example.newsapp.databinding.ItemNewsBinding
import com.example.newsapp.model.newsResponse.News
import com.github.marlonlom.utilities.timeago.TimeAgo

class NewsAdapter(var newsList: List<News?>? = null) :
    RecyclerView.Adapter<NewsAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding = ItemNewsBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = newsList?.size ?: 0

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bind(newsList?.get(position))
    }

    class ViewHolder(val binding: ItemNewsBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(news: News?) {
            binding.title.text = news?.title
            binding.author.text = news?.author
            binding.date.text = news?.publishedAt
            Glide.with(binding.root)
                .load(news?.urlToImage)
                .error(R.drawable.image_failed)
                .into(binding.image)
            /// "2025-02-14T18:47:08Z"
            val formattedDate = news?.getPublishedAtInMillis()?.let { TimeAgo.using(it) }
            binding.date.text = formattedDate
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun changeData(newList: List<News?>?) {
        this.newsList = newList
        notifyDataSetChanged()
    }
}