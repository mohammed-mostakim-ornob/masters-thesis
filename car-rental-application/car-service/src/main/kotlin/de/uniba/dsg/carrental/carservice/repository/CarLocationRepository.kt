package de.uniba.dsg.carrental.carservice.repository

import de.uniba.dsg.carrental.carservice.model.data.CarLocation
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CarLocationRepository : JpaRepository<CarLocation, Long> {
    fun getAllByLocationCode(locationCode: String): List<CarLocation>
}
