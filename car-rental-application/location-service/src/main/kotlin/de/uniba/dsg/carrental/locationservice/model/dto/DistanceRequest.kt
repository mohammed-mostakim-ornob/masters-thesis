package de.uniba.dsg.carrental.locationservice.model.dto

import java.io.Serializable

data class DistanceRequest(
    val from: String,
    val to: String
) : Serializable
