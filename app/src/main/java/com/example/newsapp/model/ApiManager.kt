package com.example.newsapp.model

import android.util.Log
import com.example.newsapp.api.WebServices
import com.example.newsapp.api.interceptors.AuthInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiManager {

    companion object {
        private var retrofit: Retrofit? = null

        private fun initRetrofit(): Retrofit {

            val loggingInterceptor = HttpLoggingInterceptor { message: String ->
                Log.e("api", message)
            }
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            val okHTTPClient = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .addInterceptor(AuthInterceptor())
                .build()
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .client(okHTTPClient)
                    .baseUrl("https://newsapi.org/")
                    .addConverterFactory(
                        GsonConverterFactory.create()

                    )
                    .build()
            }
            return retrofit!!
        }

        fun wedServices(): WebServices {

            return initRetrofit().create(WebServices::class.java)

        }
    }
}