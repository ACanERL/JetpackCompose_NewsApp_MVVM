package com.example.jetpackcompose_newsmvvm.di

import com.example.jetpackcompose_newsmvvm.data.ApiConstants
import com.example.jetpackcompose_newsmvvm.data.NewsService
import com.example.jetpackcompose_newsmvvm.data.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {


    @Singleton
    @Provides
    fun provideRetrofitInstance(): NewsService = Retrofit.Builder()
        .baseUrl(ApiConstants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(NewsService::class.java)


    @Singleton
    @Provides
    fun provideNewsRepository(newsApiService: NewsService): NewsRepository =
        NewsRepository(newsApiService)
}