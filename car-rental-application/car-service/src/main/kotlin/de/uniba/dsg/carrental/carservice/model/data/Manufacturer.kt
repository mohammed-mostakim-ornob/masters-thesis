package de.uniba.dsg.carrental.carservice.model.data

import org.springframework.hateoas.RepresentationModel
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class Manufacturer(
    @Id
    val name: String
) : RepresentationModel<Manufacturer>()