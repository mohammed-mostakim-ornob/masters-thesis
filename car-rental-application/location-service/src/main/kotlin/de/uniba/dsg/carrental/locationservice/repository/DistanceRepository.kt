package de.uniba.dsg.carrental.locationservice.repository

import de.uniba.dsg.carrental.locationservice.model.data.Distance
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface DistanceRepository : JpaRepository<Distance, Long> {
    fun findByFromCodeAndToCode(fromCode: String, toCode: String): Distance?
}
