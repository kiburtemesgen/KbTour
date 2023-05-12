package com.example.kbtour.data.services

import com.example.kbtour.models.place.Place
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("place")
    suspend fun getPlaces(): Place
    @GET("place")
    suspend fun searchPlaces(@Query("query") searchQuery: String): Place
}