package com.example.newsapp.categories

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.databinding.ItemCategoryBinding
import com.example.newsapp.model.Category

class CategoriesAdapter(
    val items: List<Category> = Category.getCategories(),
    val onCategoryClick: ((category: Category) -> Unit)
) :
    RecyclerView.Adapter<CategoriesAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding = ItemCategoryBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bind(items[position])
        holder.itemView.setOnClickListener {
            onCategoryClick(items[position])
        }
    }

    class ViewHolder(val binding: ItemCategoryBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(category: Category?) {

            category?.let { binding.imageCat.setImageResource(it.imageRes) }


        }
    }


}