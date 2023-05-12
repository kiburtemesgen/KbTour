package com.example.kbtour.models.place

data class Place (
    val place: List<PlaceModel>,
    val status: String
    )
data class PlaceModel(
    val title: String,
    val description: String,
    val imageUrl: String
)