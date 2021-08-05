package de.uniba.dsg.carrental.rentservice.model.dto

data class DistanceResponse(
    val from: String?,
    val to: String?,
    val distance: Double,
    val isSuccessful: Boolean,
    val errorMessage: String?
)
