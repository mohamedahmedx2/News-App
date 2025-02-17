package com.example.newsapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.newsapp.categories.CategoriesFragment
import com.example.newsapp.databinding.ActivityHomeBinding
import com.example.newsapp.model.Category
import com.example.newsapp.news.NewsFragment

class HomeActivity : AppCompatActivity() {

    lateinit var viewBinding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        viewBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)


        showMainFragment(
            CategoriesFragment.getInstance(
                onCategoryClickCallBack = ::onCategoryClick
            )
        )

        setSupportActionBar(viewBinding.appBarActivityHome.toolbar)
        viewBinding.appBarActivityHome.toolbar.setNavigationOnClickListener {
            viewBinding.drawerLayout.open()
        }
        viewBinding.navView.setNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    showMainFragment(
                        CategoriesFragment.getInstance(
                            onCategoryClickCallBack = ::onCategoryClick
                        )
                    )
                }

                else -> {}
            }
            viewBinding.drawerLayout.closeDrawers()
            return@setNavigationItemSelectedListener true

        }
    }

    fun onCategoryClick(category: Category) {
        showNewsFragment(category)
    }

    private fun showNewsFragment(category: Category) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, NewsFragment.getInstance(category))
            .addToBackStack(null)
            .commit()
    }

    private fun showMainFragment(fragment: CategoriesFragment) {

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}