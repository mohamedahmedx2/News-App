package com.example.newsapp.search

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.domain.model.News
import com.example.newsapp.R
import com.example.newsapp.databinding.ItemNewsSearchBinding
import com.github.marlonlom.utilities.timeago.TimeAgo

class NewsSearchAdapter(var newsList: List<News?>? = null) :
    RecyclerView.Adapter<NewsSearchAdapter.NewsViewHolder>() {

    class NewsViewHolder(val binding: ItemNewsSearchBinding) :
        RecyclerView.ViewHolder(binding.root) {
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding =
            ItemNewsSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(newsList?.get(position))
//
//        holder.binding.cardItemSearch.setOnClickListener {
//
//        }


    }

    override fun getItemCount(): Int = newsList?.size ?: 0

    @SuppressLint("NotifyDataSetChanged")
    fun changeData(newList: List<News?>?) {
        if (newList != null) {
            this.newsList = newList
        }
        notifyDataSetChanged()
    }


}
