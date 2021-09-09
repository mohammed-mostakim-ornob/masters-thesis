package de.uniba.dsg.carrental.rentservice.model.dto

data class CarResponse(
    val id: Long?,
    val manufacturer: String?,
    val model: String?,
    val rentPerKilo: Double,
    val isSuccessful: Boolean,
    val errorMessage: String?
)
