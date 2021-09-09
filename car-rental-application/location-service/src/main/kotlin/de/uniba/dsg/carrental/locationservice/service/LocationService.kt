package de.uniba.dsg.carrental.locationservice.service

import de.uniba.dsg.carrental.locationservice.exception.EntityNotFoundException
import de.uniba.dsg.carrental.locationservice.model.data.Location
import de.uniba.dsg.carrental.locationservice.repository.LocationRepository
import org.springframework.stereotype.Service

@Service
class LocationService(private val locationRepository: LocationRepository) {

    fun getAllLocations(): List<Location> {
        return locationRepository.findAll()
    }

    @Throws(EntityNotFoundException::class)
    fun getLocationByCode(code: String): Location {
        return locationRepository.findByCode(code) ?: throw EntityNotFoundException("Location not found with Code: $code")
    }
}