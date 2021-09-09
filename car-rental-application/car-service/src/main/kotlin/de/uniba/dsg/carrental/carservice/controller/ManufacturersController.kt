package de.uniba.dsg.carrental.carservice.controller

import de.uniba.dsg.carrental.carservice.exception.EntityNotFoundException
import de.uniba.dsg.carrental.carservice.model.data.Manufacturer
import de.uniba.dsg.carrental.carservice.service.CarService
import de.uniba.dsg.carrental.carservice.service.ManufactureService
import org.springframework.hateoas.CollectionModel
import org.springframework.hateoas.Link
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/manufacturers")
class ManufacturersController(private val manufactureService: ManufactureService, private val carService: CarService) {

    @GetMapping(produces = ["application/v1+json"])
    fun getManufacturersV1(): ResponseEntity<CollectionModel<Manufacturer>> {
        val manufacturers = manufactureService.getAllManufacturers()

        manufacturers.forEach {
            it.add(linkTo(
                methodOn(ManufacturersController::class.java).getManufacturerCarsV1(it.name)
            ).withSelfRel())
        }

        val link: Link = linkTo(
            methodOn(ManufacturersController::class.java).getManufacturersV1()
        ).withRel("getCars")

        return ResponseEntity(CollectionModel.of(manufacturers, link), HttpStatus.OK)
    }

    @GetMapping(produces = ["application/v2+json"])
    fun getManufacturersV2(): ResponseEntity<CollectionModel<Manufacturer>> {
        val manufacturers = manufactureService.getAllManufacturers()

        manufacturers.forEach {
            it.add(linkTo(
                methodOn(ManufacturersController::class.java).getManufacturerCarsV2(it.name)
            ).withSelfRel())
        }

        val link: Link = linkTo(
            methodOn(ManufacturersController::class.java).getManufacturersV2()
        ).withRel("getCars")

        return ResponseEntity(CollectionModel.of(manufacturers, link), HttpStatus.OK)
    }

    @GetMapping("/{name}/cars", produces = ["application/v1+json"])
    fun getManufacturerCarsV1(@PathVariable name: String): ResponseEntity<Any> {
        return try {
            val manufacturer = manufactureService.getManufacturer(name)

            val cars = carService.getCarsByManufacturer(manufacturer.name)

            cars.forEach {
                it.add(linkTo(
                    methodOn(CarsController::class.java).getCarV1(it.id)
                ).withSelfRel())

                it.manufacturer.add(linkTo(
                    methodOn(ManufacturersController::class.java).getManufacturerCarsV1(it.manufacturer.name)).withRel("getCars")
                )
            }

            val link: Link = linkTo(
                methodOn(ManufacturersController::class.java).getManufacturerCarsV1(name)
            ).withSelfRel()

            ResponseEntity(CollectionModel.of(cars, link), HttpStatus.OK)
        } catch (ex: EntityNotFoundException) {
            ResponseEntity(ex.message, HttpStatus.NOT_FOUND)
        }
    }

    @GetMapping("/{name}/cars", produces = ["application/v2+json"])
    fun getManufacturerCarsV2(@PathVariable name: String): ResponseEntity<Any> {
        return try {
            val manufacturer = manufactureService.getManufacturer(name)

            val cars = carService.getCarsByManufacturer(manufacturer.name)

            cars.forEach {
                it.add(linkTo(
                    methodOn(CarsController::class.java).getCarV2(it.id)
                ).withSelfRel())

                it.manufacturer.add(linkTo(
                    methodOn(ManufacturersController::class.java).getManufacturerCarsV2(it.manufacturer.name)).withRel("getCars")
                )
            }

            val link: Link = linkTo(
                methodOn(ManufacturersController::class.java).getManufacturerCarsV2(name)
            ).withSelfRel()

            ResponseEntity(CollectionModel.of(cars, link), HttpStatus.OK)
        } catch (ex: EntityNotFoundException) {
            ResponseEntity(ex.message, HttpStatus.NOT_FOUND)
        }
    }
}