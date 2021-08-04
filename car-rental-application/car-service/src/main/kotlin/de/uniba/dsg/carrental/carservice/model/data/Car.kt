package de.uniba.dsg.carrental.carservice.model.data

import org.springframework.hateoas.RepresentationModel
import javax.persistence.*

@Entity
data class Car(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    @ManyToOne
    val manufacturer: Manufacturer,
    val model: String,
    val rentPerKilo: Double
) : RepresentationModel<Car>()