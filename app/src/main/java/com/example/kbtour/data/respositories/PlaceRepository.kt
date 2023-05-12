package com.example.kbtour.data.respositories

import android.util.Log
import com.example.kbtour.data.services.ApiService
import com.example.kbtour.models.place.Place
import com.example.kbtour.utils.network.DataState
import kotlinx.coroutines.delay
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PlaceRepository @Inject constructor(private val apiService: ApiService) {
    private val refreshIntervalMS: Long = 5000
    suspend fun getPlaces():  Flow<DataState<Place>> =flow{

            emit(DataState.Loading)
            try {
                val placeResult = apiService.getPlaces()
                emit(DataState.Success(placeResult))
            } catch (e: Exception){
                emit(DataState.Error(e))
            }
        }

    suspend fun searchPlace(searchKey: String): Flow<DataState<Place>> = flow {
        emit(DataState.Loading)
        try {
            val result = apiService.searchPlaces(searchKey)
            emit(DataState.Success(result))
        } catch (e: Exception){
            emit(DataState.Error(e))
        }
    }


}