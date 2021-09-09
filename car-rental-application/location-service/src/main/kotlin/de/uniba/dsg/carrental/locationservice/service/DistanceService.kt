package de.uniba.dsg.carrental.locationservice.service

import de.uniba.dsg.carrental.locationservice.exception.EntityNotFoundException
import de.uniba.dsg.carrental.locationservice.model.data.Distance
import de.uniba.dsg.carrental.locationservice.repository.DistanceRepository
import org.springframework.stereotype.Service
import kotlin.jvm.Throws

@Service
class DistanceService(private val distanceRepository: DistanceRepository) {

    @Throws(EntityNotFoundException::class)
    fun getLocationByFromAndToCode(from: String, to: String): Distance {
        return distanceRepository.findByFromCodeAndToCode(from, to) ?: throw EntityNotFoundException("No distance found for locations from: $from and to: $to")
    }
}