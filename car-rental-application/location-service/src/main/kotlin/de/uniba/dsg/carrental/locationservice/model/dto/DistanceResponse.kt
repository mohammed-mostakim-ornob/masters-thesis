package de.uniba.dsg.carrental.locationservice.model.dto

import java.io.Serializable

data class DistanceResponse(
    val from: String,
    val to: String,
    val distance: Double,
    val isSuccessful: Boolean,
    val errorMessage: String?
) : Serializable
