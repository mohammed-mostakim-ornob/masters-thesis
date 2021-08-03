package de.uniba.dsg.carrental.locationservice.controller

import de.uniba.dsg.carrental.locationservice.exception.EntityNotFoundException
import de.uniba.dsg.carrental.locationservice.model.data.Location
import de.uniba.dsg.carrental.locationservice.service.LocationService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("locations")
class LocationsController(private val locationService: LocationService) {

    @GetMapping(produces = ["application/v1+json"])
    fun getLocationsV1(): ResponseEntity<List<Location>> {
        val locations = locationService.getAllLocations()

        return ResponseEntity(locations, HttpStatus.OK)
    }

    @GetMapping(produces = ["application/v2+json"])
    fun getLocationsV2(): ResponseEntity<List<Location>> {
        val locations = locationService.getAllLocations()

        return ResponseEntity(locations, HttpStatus.CREATED)
    }

    @GetMapping("{code}", produces = ["application/v1+json"])
    fun getLocationV1(@PathVariable code: String): ResponseEntity<Any> {
        return try {
            val location = locationService.getLocationByCode(code)

            ResponseEntity(location, HttpStatus.OK)
        } catch (ex: EntityNotFoundException) {
            ResponseEntity(ex.message, HttpStatus.NOT_FOUND)
        }
    }

    @GetMapping("{code}", produces = ["application/v2+json"])
    fun getLocationV2(@PathVariable code: String): ResponseEntity<Any> {
        return try {
            val location = locationService.getLocationByCode(code)

            ResponseEntity(location, HttpStatus.OK)
        } catch (ex: EntityNotFoundException) {
            ResponseEntity(ex.message, HttpStatus.NOT_FOUND)
        }
    }
}