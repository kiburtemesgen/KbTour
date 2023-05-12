package com.example.kbtour.di

import com.example.kbtour.data.respositories.PlaceRepository
import com.example.kbtour.data.services.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Singleton
    @Provides
    fun providePlaceRepository(apiService: ApiService): PlaceRepository {
        return PlaceRepository(apiService)
    }
}