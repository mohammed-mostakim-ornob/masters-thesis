package de.uniba.dsg.carrental.carservice.controller

import de.uniba.dsg.carrental.carservice.exception.EntityNotFoundException
import de.uniba.dsg.carrental.carservice.model.data.Car
import de.uniba.dsg.carrental.carservice.service.CarService
import org.springframework.hateoas.CollectionModel
import org.springframework.hateoas.Link
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("cars")
class CarsController(private val carService: CarService) {

    @GetMapping(produces = ["application/v1+json"])
    fun getCarsV1(@RequestParam(required = false) location: String?): ResponseEntity<CollectionModel<Car>> {
        val cars = if (location == null) carService.getAllCars() else carService.getCarsByLocation(location)

        cars.forEach {
            it.add(linkTo(
                methodOn(CarsController::class.java).getCarV1(it.id)
            ).withSelfRel())

            it.manufacturer.add(linkTo(
                methodOn(ManufacturersController::class.java).getManufacturerCarsV1(it.manufacturer.name)).withRel("getCars")
            )
        }

        val link: Link = linkTo(
            methodOn(CarsController::class.java).getCarsV1(location)
        ).withSelfRel()

        return ResponseEntity(CollectionModel.of(cars, link), HttpStatus.OK)
    }

    @GetMapping(produces = ["application/v2+json"])
    fun getCarsV2(@RequestParam(required = false) location: String?): ResponseEntity<CollectionModel<Car>> {
        val cars = if (location == null) carService.getAllCars() else carService.getCarsByLocation(location)

        cars.forEach {
            it.add(linkTo(
                methodOn(CarsController::class.java).getCarV2(it.id)
            ).withSelfRel())

            it.manufacturer.add(linkTo(
                methodOn(ManufacturersController::class.java).getManufacturerCarsV2(it.manufacturer.name)).withRel("getCars")
            )
        }

        val link: Link = linkTo(
            methodOn(CarsController::class.java).getCarsV2(location)
        ).withSelfRel()

        return ResponseEntity(CollectionModel.of(cars, link), HttpStatus.OK)
    }

    @GetMapping("{id}", produces = ["application/v1+json"])
    fun getCarV1(@PathVariable id: Long): ResponseEntity<Any> {
        return try {
            val car = carService.getCar(id)

            car.add(linkTo(
                methodOn(CarsController::class.java).getCarV1(car.id)
            ).withSelfRel())

            car.manufacturer.add(linkTo(
                methodOn(ManufacturersController::class.java).getManufacturerCarsV1(car.manufacturer.name)).withRel("getCars")
            )

            ResponseEntity(car, HttpStatus.OK)
        } catch (ex: EntityNotFoundException) {
            ResponseEntity(ex.message, HttpStatus.NOT_FOUND)
        }
    }

    @GetMapping("{id}", produces = ["application/v2+json"])
    fun getCarV2(@PathVariable id: Long): ResponseEntity<Any> {
        return try {
            val car = carService.getCar(id)

            car.add(linkTo(
                methodOn(CarsController::class.java).getCarV2(car.id)
            ).withSelfRel())

            car.manufacturer.add(linkTo(
                methodOn(ManufacturersController::class.java).getManufacturerCarsV2(car.manufacturer.name)).withRel("getCars")
            )

            ResponseEntity(car, HttpStatus.OK)
        } catch (ex: EntityNotFoundException) {
            ResponseEntity(ex.message, HttpStatus.NOT_FOUND)
        }
    }
}