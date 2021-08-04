package de.uniba.dsg.carrental.carservice.repository

import de.uniba.dsg.carrental.carservice.model.data.Manufacturer
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ManufacturerRepository : JpaRepository<Manufacturer, String>
