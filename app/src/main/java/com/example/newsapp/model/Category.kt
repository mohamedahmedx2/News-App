package com.example.newsapp.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.newsapp.R

data class Category(
    val id: String,
    @StringRes
    val title: Int,
    @DrawableRes
    val imageRes: Int,
) {
    companion object {


        ///// business entertainment general health science sports technology
        fun getCategories(): List<Category> =
            listOf(
                Category(
                    id = "business",
                    title = R.string.business,
                    imageRes = R.drawable.cat_business
                ),
                Category(
                    id = "entertainment",
                    title = R.string.entertainment,
                    imageRes = R.drawable.cat_intertainment
                ),
                Category(
                    id = "general",
                    title = R.string.general,
                    imageRes = R.drawable.cat_general
                ),
                Category(
                    id = "health",
                    title = R.string.health,
                    imageRes = R.drawable.cat_health
                ),
                Category(
                    id = "science",
                    title = R.string.science,
                    imageRes = R.drawable.cat_science
                ),
                Category(
                    id = "sports",
                    title = R.string.sports,
                    imageRes = R.drawable.cat_sports
                ),
                Category(
                    id = "technology",
                    title = R.string.technology,
                    imageRes = R.drawable.cat_tech
                ),
            )
    }
}

