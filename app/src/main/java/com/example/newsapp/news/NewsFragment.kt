package com.example.newsapp.news

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.domain.model.News
import com.example.domain.model.Source
import com.example.newsapp.databinding.FragmentNewsBinding
import com.example.newsapp.handel.ErrorState
import com.example.newsapp.model.Category
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import dagger.hilt.android.AndroidEntryPoint

@SuppressLint("StaticFieldLeak")
lateinit var viewBinding: FragmentNewsBinding
//lateinit var binding: AppBarActivityHomeBinding

@AndroidEntryPoint
class NewsFragment : Fragment() {

    companion object {
        fun getInstance(category: Category): NewsFragment {
            val fragment = NewsFragment()
            fragment.category = category
            return fragment
        }
    }

    lateinit var category: Category
    lateinit var viewModel: NewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[NewsViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentNewsBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        viewModel.loadSources(categoryId = category.id)
        observerLiveData()
    }

    private fun observerLiveData() {
        viewModel.sourceLiveData.observe(viewLifecycleOwner, Observer { data ->
            bindTapLayout(data)
        })

        viewModel.showLoading.observe(viewLifecycleOwner) { isLoading ->

            if (isLoading) {
                showLoading()

            }

        }

        viewModel.toastMessage.observe(viewLifecycleOwner) { message ->
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
        }
        viewModel.errorState.observe(viewLifecycleOwner) { state ->
            showErrorView(state)
        }
        viewModel.newsLiveData.observe(viewLifecycleOwner, Observer { data ->
            showSuccessView()
            bindNewsList(data)
        })
    }

    @SuppressLint("ShowToast")
    private fun initViews() {
        viewBinding.newsRecyclerview.adapter = adapter

    }

    private fun bindTapLayout(sources: List<Source?>?) {
        viewBinding.loadingView.isVisible = false
        viewBinding.errorView.isVisible = false
        sources?.forEach { sources ->
            val tab = viewBinding.sourcesTabs.newTab()
            tab.text = sources?.name
            tab.tag = sources
            viewBinding.sourcesTabs.addTab(tab)

        }
        viewBinding.sourcesTabs.addOnTabSelectedListener(
            object : OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    val source = tab?.tag as Source?
                    source?.sourceId?.let { viewModel.loadNews(it) }


                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {
                }

                override fun onTabReselected(tab: TabLayout.Tab?) {
                    val source = tab?.tag as Source?
                    source?.sourceId?.let { viewModel.loadNews(it) }
                }

            }
        )
        viewBinding.sourcesTabs.getTabAt(0)?.select()


    }

    val adapter = NewsAdapter()
    private fun bindNewsList(newsList: List<News?>?) {
        //    adapter.newsList = newsList
        adapter.changeData(newsList)
    }

    fun showLoading() {
        viewBinding.loadingView.isVisible = true
        viewBinding.errorView.isVisible = false
    }

    fun showSuccessView() {
        viewBinding.loadingView.isVisible = false
        viewBinding.errorView.isVisible = false
    }

    fun showErrorView(errorState: ErrorState) {
        viewBinding.loadingView.isVisible = false
        viewBinding.errorView.isVisible = true
        viewBinding.errorMessage.text = errorState.errorMessenger
        viewBinding.tryAgain.setOnClickListener {
            errorState.onRetry?.invoke()
        }
    }


}

