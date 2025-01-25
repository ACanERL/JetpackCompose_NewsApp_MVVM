package com.example.jetpackcompose_newsmvvm.presentation

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.jetpackcompose_newsmvvm.data.dto.Article
import com.example.jetpackcompose_newsmvvm.data.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow

import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(private val newsRepository: NewsRepository) : ViewModel() {
    fun getBreakingNews(query: String): Flow<PagingData<Article>> =
        newsRepository.getNews(query).cachedIn(viewModelScope)

}