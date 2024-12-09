package com.example.newsapp.di

import com.example.newsapp.datasource.HaberlerDataSource
import com.example.newsapp.repository.HaberlerRepository
import com.example.newsapp.retrofit.ApiUtils
import com.example.newsapp.retrofit.HaberlerDao
import com.example.newsapp.retrofit.RetrofitClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideHaberlerRepository(hds: HaberlerDataSource) : HaberlerRepository{
        return HaberlerRepository(hds)
    }

    @Provides
    @Singleton
    fun provideHaberlerDataSource(hdao: HaberlerDao) : HaberlerDataSource{
        return HaberlerDataSource(hdao)
    }

    @Provides
    @Singleton
    fun provideHaberlerDao(retrofit: Retrofit): HaberlerDao {
        return retrofit.create(HaberlerDao::class.java)
    }

    // Retrofit Client'ı sağlamak için
    @Provides
    @Singleton
    fun provideRetrofitClient(): Retrofit {
        return RetrofitClient.getClient("https://api.collectapi.com/")
    }
}