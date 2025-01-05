package com.yesandroid.newsapp.api

import retrofit2.http.GET

interface ApiService {
    @GET("/v2/everything?q=tesla&from=2024-12-05&sortBy=publishedAt&apiKey=db701712e8f14c86bb6ff9585d588798")
    suspend fun getNews(): String
}
