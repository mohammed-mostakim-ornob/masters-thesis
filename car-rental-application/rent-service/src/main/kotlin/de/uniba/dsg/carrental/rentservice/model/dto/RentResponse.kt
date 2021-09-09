package de.uniba.dsg.carrental.rentservice.model.dto

import org.springframework.hateoas.RepresentationModel

data class RentResponse(val rent: Double) : RepresentationModel<RentResponse>()