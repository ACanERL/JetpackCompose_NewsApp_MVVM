package com.example.jetpackcompose_newsmvvm.data.dto

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)