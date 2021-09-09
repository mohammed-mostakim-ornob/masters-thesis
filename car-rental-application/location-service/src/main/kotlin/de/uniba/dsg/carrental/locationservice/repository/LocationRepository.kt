package de.uniba.dsg.carrental.locationservice.repository

import de.uniba.dsg.carrental.locationservice.model.data.Location
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface LocationRepository : JpaRepository<Location, String> {
    fun findByCode(code: String): Location?
}