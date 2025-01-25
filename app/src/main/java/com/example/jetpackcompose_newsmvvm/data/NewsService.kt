package com.example.jetpackcompose_newsmvvm.data

import com.example.jetpackcompose_newsmvvm.data.dto.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {
    @GET("everything?apiKey=${ApiConstants.API_KEY}")
    suspend fun getNews(
        @Query("q") query: String,
        @Query("page") page: Int,
    ): NewsResponse
}