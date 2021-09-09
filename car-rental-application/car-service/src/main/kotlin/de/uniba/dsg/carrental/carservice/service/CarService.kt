package de.uniba.dsg.carrental.carservice.service

import de.uniba.dsg.carrental.carservice.exception.EntityNotFoundException
import de.uniba.dsg.carrental.carservice.model.data.Car
import de.uniba.dsg.carrental.carservice.model.data.CarLocation
import de.uniba.dsg.carrental.carservice.repository.CarLocationRepository
import de.uniba.dsg.carrental.carservice.repository.CarRepository
import org.springframework.stereotype.Service
import kotlin.jvm.Throws

@Service
class CarService(private val carRepository: CarRepository, private val carLocationRepository: CarLocationRepository) {
    fun getAllCars(): List<Car> {
        return carRepository.findAll()
    }

    @Throws(EntityNotFoundException::class)
    fun getCar(id: Long): Car {
        val car = carRepository.findById(id)

        if (car.isEmpty)
            throw EntityNotFoundException("No Car found with ID: $id")

        return car.get()
    }

    fun getCarsByManufacturer(manufacturerName: String): List<Car> {
        return carRepository.findCarsByManufacturerName(manufacturerName)
    }

    fun getCarsByLocation(locationCode: String): List<Car> {
        return carLocationRepository.getAllByLocationCode(locationCode)
            .map {
                it.car
            }
    }

    @Throws(EntityNotFoundException::class)
    fun getCarInLocation(locationCode: String, carId: Long): CarLocation {
        return carLocationRepository.getByLocationCodeAndCarId(locationCode, carId) ?: throw EntityNotFoundException("No Car with ID: $carId found in Location: $locationCode")
    }
}
