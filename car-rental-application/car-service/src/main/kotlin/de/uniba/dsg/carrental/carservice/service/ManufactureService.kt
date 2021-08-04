package de.uniba.dsg.carrental.carservice.service

import de.uniba.dsg.carrental.carservice.exception.EntityNotFoundException
import de.uniba.dsg.carrental.carservice.model.data.Manufacturer
import de.uniba.dsg.carrental.carservice.repository.ManufacturerRepository
import org.springframework.stereotype.Service
import kotlin.jvm.Throws

@Service
class ManufactureService(private val manufacturerRepository: ManufacturerRepository) {
    fun getAllManufacturers(): List<Manufacturer> {
        return manufacturerRepository.findAll();
    }

    @Throws(EntityNotFoundException::class)
    fun getManufacturer(name: String): Manufacturer {
        val manufacturer = manufacturerRepository.findById(name)

        if (manufacturer.isEmpty)
            throw EntityNotFoundException("No Manufacturer found with Name: $name")

        return manufacturer.get()
    }
}
