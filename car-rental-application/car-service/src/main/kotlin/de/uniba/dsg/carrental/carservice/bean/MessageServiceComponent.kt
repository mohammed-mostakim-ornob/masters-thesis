package de.uniba.dsg.carrental.carservice.bean

import com.google.gson.Gson
import de.uniba.dsg.carrental.carservice.exception.EntityNotFoundException
import de.uniba.dsg.carrental.carservice.model.dto.CarRequest
import de.uniba.dsg.carrental.carservice.model.dto.CarResponse
import de.uniba.dsg.carrental.carservice.service.CarService
import org.springframework.amqp.core.Queue
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component

@Component
class MessageServiceComponent(private val carService: CarService, private val gson: Gson) {

    @Value("\${message-service.car_rent_price_queue}")
    var queueName: String = String()

    @Bean
    fun queue(): Queue {
        return Queue(queueName, false)
    }

    @RabbitListener(queues = ["\${message-service.car_rent_price_queue}"])
    fun listen(request: String): String {
        return try {
            val carRequest = gson.fromJson(request, CarRequest::class.java)

            val car = carService.getCar(carRequest.carId)

            carService.getCarInLocation(carRequest.fromLocation, carRequest.carId)

            gson.toJson(CarResponse(car.id, car.manufacturer.name, car.model, car.rentPerKilo, true, null))
        } catch (ex: EntityNotFoundException) {
            gson.toJson(CarResponse(null, null, null, 0.0, false, ex.message))
        }
    }
}