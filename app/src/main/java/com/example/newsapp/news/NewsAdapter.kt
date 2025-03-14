package com.example.newsapp.news

import android.annotation.SuppressLint
import android.content.ContextWrapper
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.News
import com.example.newsapp.NewsBottomSheetFragment
import com.example.newsapp.databinding.ItemNewsBinding


class NewsAdapter(
    var newsList: List<News?>? = null,
) :

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

        /// holder.itemView
//        holder.binding.cardItem.setOnClickListener {
//            val newsItem = newsList?.get(position)
//            newsItem?.let { it1 -> NewsBottomSheetFragment(it1) }
//                ?.show((it.context as AppCompatActivity).supportFragmentManager, "NewsBottomSheet")
//        }


        holder.binding.cardItem.setOnClickListener {
            val newsItem = newsList?.get(position)
            val context = it.context
            val activity = when (context) {
                is AppCompatActivity -> context
                is ContextWrapper -> context.baseContext as? AppCompatActivity
                else -> null
            }

            if (activity != null && newsItem != null) {
                NewsBottomSheetFragment(newsItem)
                    .show(activity.supportFragmentManager, "NewsBottomSheet")
            } else {
                Log.e("NewsAdapter", "Context is not an AppCompatActivity or newsItem is null")
            }
        }




    }

    class ViewHolder(val binding: ItemNewsBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(news: News?) {
            binding.newsItem = news
            binding.invalidateAll()
        }
    }


    @SuppressLint("NotifyDataSetChanged")
    fun changeData(newList: List<News?>?) {
        this.newsList = newList
        notifyDataSetChanged()
    }
}