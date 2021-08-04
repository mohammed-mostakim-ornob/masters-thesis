package de.uniba.dsg.carrental.carservice.model.data

import javax.persistence.*

@Entity
data class CarLocation(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    @OneToOne
    val car: Car,
    val locationCode: String
)