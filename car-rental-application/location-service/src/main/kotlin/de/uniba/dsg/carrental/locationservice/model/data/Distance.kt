package de.uniba.dsg.carrental.locationservice.model.data

import javax.persistence.*

@Entity
data class Distance(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    @ManyToOne
    @JoinColumn(name = "from_location")
    val from: Location,
    @ManyToOne
    @JoinColumn(name = "to_location")
    val to: Location,
    val distance: Double
)
