package de.uniba.dsg.carrental.rentservice.controller

import de.uniba.dsg.carrental.rentservice.exception.BadRequestException
import de.uniba.dsg.carrental.rentservice.model.dto.RentResponse
import de.uniba.dsg.carrental.rentservice.service.RentService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn
import java.util.ArrayList

@RestController
@RequestMapping("/rent")
class RentController(private val rentService: RentService) {

    @GetMapping(produces = ["application/v1+json"])
    fun calculateRentV1(@RequestParam from: String, @RequestParam to: String, @RequestParam carId: Long): ResponseEntity<Any> {
        return try {
            validateRentRequestQueryParams(from, to, carId)

            val rent = rentService.calculateRent(from, to, carId)

            val rentResponse = RentResponse(rent).add(linkTo(
                methodOn(RentController::class.java).calculateRentV1(from, to, carId)
            ).withSelfRel())

            ResponseEntity(rentResponse, HttpStatus.OK)
        } catch (ex: BadRequestException) {
            ResponseEntity(ex.message, HttpStatus.BAD_REQUEST)
        }
    }

    @GetMapping(produces = ["application/v2+json"])
    fun calculateRentV2(@RequestParam from: String,@RequestParam to: String,@RequestParam carId: Long): ResponseEntity<Any> {
        return try {
            validateRentRequestQueryParams(from, to, carId)

            val rent = rentService.calculateRent(from, to, carId)

            val rentResponse = RentResponse(rent).add(linkTo(
                methodOn(RentController::class.java).calculateRentV2(from, to, carId)
            ).withSelfRel())

            ResponseEntity(rentResponse, HttpStatus.OK)
        } catch (ex: BadRequestException) {
            ResponseEntity(ex.message, HttpStatus.BAD_REQUEST)
        }
    }

    private fun validateRentRequestQueryParams(from: String, to: String, carId: Long) {
        val errors: ArrayList<String> = ArrayList()

        if (from.isEmpty())
            errors.add("Invalid From location")

        if (to.isEmpty())
            errors.add("Invalid location")

        if (carId <= 0)
            errors.add("Invalid Car ID")

        if (errors.isNotEmpty())
            throw BadRequestException(errors.joinToString(", "))
    }
}