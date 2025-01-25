package com.example.jetpackcompose_newsmvvm.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.jetpackcompose_newsmvvm.data.ApiConstants
import com.example.jetpackcompose_newsmvvm.data.NewsDataSource
import com.example.jetpackcompose_newsmvvm.data.NewsService
import javax.inject.Inject


class NewsRepository @Inject constructor(
    private val newsService: NewsService
) {
    fun getNews(query: String) = Pager(
        config = PagingConfig(
            pageSize = ApiConstants.PAGE_SIZE,
        ),
        pagingSourceFactory = {
            NewsDataSource(newsService, query)
        }
    ).flow
}