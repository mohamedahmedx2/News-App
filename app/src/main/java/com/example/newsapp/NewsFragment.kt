package com.example.newsapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.newsapp.api.model.ApiManager
import com.example.newsapp.api.model.ErrorResponse
import com.example.newsapp.api.model.newsResponse.News
import com.example.newsapp.api.model.newsResponse.NewsResponse
import com.example.newsapp.api.model.sourcesResponse.Source
import com.example.newsapp.api.model.sourcesResponse.SourcesResponse
import com.example.newsapp.databinding.FragmentNewsBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@SuppressLint("StaticFieldLeak")
lateinit var viewBinding: FragmentNewsBinding

class NewsFragment : Fragment() {

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
        loadSources()
    }

    private fun initViews() {
        viewBinding.newsRecyclerview.adapter = adapter

    }

    private fun loadSources() {
        showLoading()
        ApiManager.wedServices().getSources()
            .enqueue(object : Callback<SourcesResponse> {
                override fun onResponse(
                    call: Call<SourcesResponse>,
                    response: Response<SourcesResponse>
                ) {
                    if (!response.isSuccessful) {
                        val errorResponse = Gson().fromJson(
                            response.errorBody()?.string(),
                            SourcesResponse::class.java
                        )
                        val message = errorResponse.message ?: "Something went Wrong"
                        showErrorView(message,
                            onTryAgainClick = {
                                loadSources()
                            })
                        return
                    }
                    bindTapLayout(response.body()?.sources)
                }

                override fun onFailure(call: Call<SourcesResponse>, error: Throwable) {
                    showErrorView(error.localizedMessage ?: "Something went Wrong",
                        onTryAgainClick = {
                            loadSources()
                        }
                    )
                }

            })
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
                    source?.id?.let { loadNews(it) }


                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {
                }

                override fun onTabReselected(tab: TabLayout.Tab?) {
                    val source = tab?.tag as Source?
                    source?.id?.let { loadNews(it) }
                }

            }
        )
        viewBinding.sourcesTabs.getTabAt(0)?.select()
    }

    private fun loadNews(sourceId: String) {
        showLoading()
        ApiManager.wedServices().getNews(source = sourceId)
            .enqueue(object : Callback<NewsResponse> {
                override fun onResponse(
                    call: Call<NewsResponse>,
                    response: Response<NewsResponse>
                ) {
                    if (!response.isSuccessful) {
                        val errorResponse = Gson().fromJson(
                            response.errorBody()?.string(),
                            ErrorResponse::class.java

                        )
                        val message = errorResponse.message ?: "Something went Wrong"
                        showErrorView(message,
                            onTryAgainClick = {
                                loadNews(sourceId)
                            })
                        return
                    }
                    showSuccessView()
                    bindNewsList(response.body()?.newsList)
                }

                override fun onFailure(call: Call<NewsResponse>, throwable: Throwable) {
                    showErrorView(throwable.localizedMessage ?: "Something went wrong",
                        onTryAgainClick = {
                            loadNews(sourceId)
                        }
                    )
                }

            })
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

    fun showErrorView(errorMessage: String, onTryAgainClick: () -> Unit) {
        viewBinding.loadingView.isVisible = false
        viewBinding.errorView.isVisible = true
        viewBinding.errorMessage.text = errorMessage
        viewBinding.tryAgain.setOnClickListener {
            onTryAgainClick.invoke()
        }
    }

}

