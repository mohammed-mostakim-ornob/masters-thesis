package de.uniba.dsg.carrental.carservice.repository

import de.uniba.dsg.carrental.carservice.model.data.Car
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CarRepository : JpaRepository<Car, Long> {
    fun findCarsByManufacturerName(manufacturerName: String): List<Car>
}
