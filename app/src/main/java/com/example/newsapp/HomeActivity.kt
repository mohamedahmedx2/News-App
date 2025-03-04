package com.example.newsapp

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.newsapp.categories.CategoriesFragment
import com.example.newsapp.databinding.ActivityHomeBinding
import com.example.newsapp.model.Category
import com.example.newsapp.news.NewsFragment
import com.example.newsapp.search.SearchActivity

class HomeActivity : AppCompatActivity() {

    private lateinit var searchEditText: EditText
    lateinit var viewBinding: ActivityHomeBinding
    @SuppressLint("ClickableViewAccessibility", "UseSwitchCompatOrMaterialCode")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        viewBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)


        val toolbar: Toolbar = viewBinding.appBarActivityHome.toolbar
        setSupportActionBar(toolbar)

        viewBinding.appBarActivityHome.iconSearch.setOnClickListener{
                        val intent = Intent(this, SearchActivity::class.java)
                        startActivity(intent)
        }


        val itemsTheme = listOf("Dark","Light")
        val adapterItemsTheme  = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, itemsTheme)
      viewBinding.autoCompleteTheme.setAdapter(adapterItemsTheme)
        viewBinding.autoCompleteTheme.setOnClickListener {
            viewBinding.autoCompleteTheme.showDropDown()
        }

        val itemsLanguage = listOf("Arabic","English")
        val adapterItemsLanguage = ArrayAdapter(this,android.R.layout.simple_dropdown_item_1line,itemsLanguage)
        viewBinding.autoCompleteLanguage.setAdapter(adapterItemsLanguage)
        viewBinding.autoCompleteLanguage.setOnClickListener {
            viewBinding.autoCompleteLanguage.showDropDown()
        }

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



    private fun showKeyboard() {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(searchEditText, InputMethodManager.SHOW_IMPLICIT)
    }

    private fun hideKeyboard() {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(searchEditText.windowToken, 0)
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
