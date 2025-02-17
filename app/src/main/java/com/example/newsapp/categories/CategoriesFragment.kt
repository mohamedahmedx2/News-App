package com.example.newsapp.categories


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.newsapp.databinding.FragmentCategoriesBinding
import com.example.newsapp.model.Category

class CategoriesFragment : Fragment() {

    companion object {
        fun getInstance(onCategoryClickCallBack: OnCategoryClickCallBack): CategoriesFragment {

            val fragment = CategoriesFragment()
            fragment.onCategoryClickCallBack = onCategoryClickCallBack
            return fragment
        }
    }

    lateinit var viewBinding: FragmentCategoriesBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentCategoriesBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycleView()

    }

    val adapter = CategoriesAdapter(
        onCategoryClick = ::onCategoryClick
    )

    private fun onCategoryClick(category: Category) {
        onCategoryClickCallBack?.onCategoryClick(category)
    }

    fun interface OnCategoryClickCallBack {
        fun onCategoryClick(category: Category)
    }

    private var onCategoryClickCallBack: OnCategoryClickCallBack? = null

    private fun initRecycleView() {
        viewBinding.categoriesRecyclerview.adapter = adapter
    }
}