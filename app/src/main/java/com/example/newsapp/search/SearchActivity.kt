package com.example.newsapp.search

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.domain.model.News
import com.example.newsapp.R
import com.example.newsapp.databinding.ActivitySearchBinding
import com.example.newsapp.news.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchActivity : AppCompatActivity() {

    lateinit var viewBinding: ActivitySearchBinding
    val adapter = NewsSearchAdapter()
    lateinit var viewModel: NewsViewModel


    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        viewBinding.newsRecyclerviewSearch.adapter = adapter
        viewModel = ViewModelProvider(this)[NewsViewModel::class.java]

        observerLiveData()
        viewBinding.textInputSearch.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                val query = viewBinding.textInputSearch.text.toString().trim()

                if (query.isNotEmpty()) {
                    viewModel.searchNews(query) // تنفيذ البحث
                    hideKeyboard(v) // إخفاء لوحة المفاتيح
                }

                true
            } else {
                false
            }
        }


// مراقبة التغير في النص
        viewBinding.textInputSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val query = s.toString().trim()

                if (query.isEmpty()) {
                    // عند تفريغ مربع البحث، أخفِ الأخبار
                    viewBinding.textInputSearch.setCompoundDrawablesRelativeWithIntrinsicBounds(
                        R.drawable.icon_search, 0, 0, 0
                    )
                    viewBinding.newsRecyclerviewSearch.visibility = View.GONE
                } else {
                    // عند إدخال نص، أظهر أيقونة X
                    viewBinding.textInputSearch.setCompoundDrawablesRelativeWithIntrinsicBounds(
                        R.drawable.icon_search, 0, R.drawable.icon_cancel, 0
                    )

                    // **إجراء البحث تلقائيًا بعد إدخال النص**
                    viewModel.searchNews(query)
                    viewBinding.newsRecyclerviewSearch.visibility =
                        View.VISIBLE // تأكد من عرض الأخبار
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

// التعامل مع زر البحث في لوحة المفاتيح
        viewBinding.textInputSearch.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                val query = viewBinding.textInputSearch.text.toString().trim()
                if (query.isNotEmpty()) {
                    // نفذ البحث بناءً على الكلمة المكتوبة
                    viewModel.searchNews(query) // دالة تقوم بجلب الأخبار وعرضها في recyclerView
                    // (ضمن دالة fetchNews تأكد من ضبط visibility على VISIBLE إذا كانت هناك نتائج)
                }
                hideKeyboard(v) // دالة لإخفاء لوحة المفاتيح
                true
            } else {
                false
            }
        }

// التعامل مع الضغط على أيقونة الـ X لمسح النص
        viewBinding.textInputSearch.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                val drawableEnd =
                    viewBinding.textInputSearch.compoundDrawablesRelative[2] // أيقونة النهاية (X)
                if (drawableEnd != null && event.rawX >= (viewBinding.textInputSearch.right - drawableEnd.bounds.width())) {
                    viewBinding.textInputSearch.text?.clear() // مسح النص
                    // سيقوم TextWatcher بإخفاء الأخبار عند كون النص فارغاً
                    return@setOnTouchListener true
                }
            }
            false
        }


    }

    fun hideKeyboard(view: View) {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    private fun observerLiveData() {
        viewModel.newsSearchLiveData.observe(this, Observer { data ->

            bindNewsSearch(data)
        })

    }

    private fun bindNewsSearch(newsList: List<News?>?) {
        //    adapter.newsList = newsList
        adapter.changeData(newsList)
    }


}

