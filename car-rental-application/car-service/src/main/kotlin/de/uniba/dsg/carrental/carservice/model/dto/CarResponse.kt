package de.uniba.dsg.carrental.carservice.model.dto

data class CarResponse(
    val id: Long?,
    val manufacturer: String?,
    val model: String?,
    val rentPerKilo: Double,
    val isSuccessful: Boolean,
    val errorMessage: String?
)