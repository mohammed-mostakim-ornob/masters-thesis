package de.uniba.dsg.carrental.locationservice.model.data

import org.springframework.hateoas.RepresentationModel
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class Location(
    @Id
    val code: String,
    val name: String
) : RepresentationModel<Location>()
