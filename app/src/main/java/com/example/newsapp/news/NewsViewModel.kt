package com.example.newsapp.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.domain.GetNewsUseCase
import com.example.domain.domain.GetSearchUseCase
import com.example.domain.domain.GetSourcesUseCase
import com.example.domain.model.News
import com.example.domain.model.Source
import com.example.newsapp.handel.ErrorState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class NewsViewModel @Inject constructor(
    val getNewsUseCase: GetNewsUseCase,
    val getSourcesUseCase: GetSourcesUseCase,
    val getSearchUseCase: GetSearchUseCase
) : ViewModel() {

    val showLoading = MutableLiveData<Boolean>(false)
    val errorState = MutableLiveData<ErrorState>()
    val sourceLiveData = MutableLiveData<List<Source?>?>()
    val newsLiveData = MutableLiveData<List<News?>?>()
    val newsSearchLiveData = MutableLiveData<List<News?>?>()
    private val _toastMessage = MutableLiveData<String>()
    val toastMessage: LiveData<String> get() = _toastMessage


    fun loadSources(categoryId: String) {
        showLoading.value = true
        viewModelScope.launch {
            try {
                val response = getSourcesUseCase.getSource(categoryId)
                sourceLiveData.value = response
            } catch (ex: Exception) {
                val message = ex.message ?: "Something went Wrong"
                errorState.value = ErrorState(
                    message,
                    onRetry = {
                        loadSources(categoryId)
                    }
                )
            }

        }

    }

    fun loadNews(sourceId: String) {
        showLoading.value = true
        viewModelScope.launch {
            try {
                val newsList = getNewsUseCase.getNews(sourceId)
                //ApiManager.wedServices().getNews(source = sourceId)
                newsLiveData.value = newsList
            } catch (ex: Exception) {
                errorState.value = ErrorState(
                    errorMessenger = ex.localizedMessage ?: "Something went wrong",
                    onRetry = {
                        loadNews(sourceId)
                    }
                )
            }


        }


    }

    fun searchNews(keyword: String) {
        viewModelScope.launch {
            try {
                val newsSearch = getSearchUseCase.searchNews(keyword)
                newsSearchLiveData.value = newsSearch
            } catch (ex: Exception) {
                _toastMessage.value = ex.localizedMessage
            }

        }


    }


//    private fun extractErrorResponse(httpException: HttpException): ErrorResponse {
//        val errorResponse = Gson().fromJson(
//            httpException.response()?.errorBody()?.string(),
//            ErrorResponse::class.java
//        )
//        return errorResponse
//    }

}