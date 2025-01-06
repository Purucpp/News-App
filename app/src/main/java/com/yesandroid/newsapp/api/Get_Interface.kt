package com.yesandroid.newsapp.api

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET

interface Get_Interface {

    @GET("/v2/everything?q=tesla&from=2024-12-06&sortBy=publishedAt&apiKey=c9d13f92a6e64eeb9571003aa6c3b7ed")
    fun getNews(): Call<ResponseBody>?

}