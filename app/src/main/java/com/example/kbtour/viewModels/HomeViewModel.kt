package com.example.kbtour.viewModels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kbtour.data.respositories.PlaceRepository
import com.example.kbtour.models.place.Place
import com.example.kbtour.utils.network.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repo: PlaceRepository): ViewModel() {
    val places: MutableState<DataState<Place>?> = mutableStateOf(null)

    init {
        getPlaceList()
    }

    fun getPlaceList(){
            viewModelScope.launch {
                repo.getPlaces().onEach {
                    places.value = it
                }.launchIn(viewModelScope)
        }
    }

    fun searchPlace(searchKey: String){
        viewModelScope.launch {
            repo.searchPlace(searchKey).onEach {
                places.value = it
            }.launchIn(viewModelScope)
        }
    }

}