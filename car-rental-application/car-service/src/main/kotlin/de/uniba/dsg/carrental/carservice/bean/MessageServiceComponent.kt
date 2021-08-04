package de.uniba.dsg.carrental.carservice.bean

import de.uniba.dsg.carrental.carservice.exception.EntityNotFoundException
import de.uniba.dsg.carrental.carservice.model.dto.CarResponse
import de.uniba.dsg.carrental.carservice.service.CarService
import org.springframework.amqp.core.Queue
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component

@Component
class MessageServiceComponent(private val carService: CarService) {

    @Value("\${message-service.car_rent_price_queue}")
    var queueName: String = String()

    @Bean
    fun queue(): Queue {
        return Queue(queueName, false)
    }

    @RabbitListener(queues = ["\${message-service.car_rent_price_queue}"])
    fun listen(carId: Long): CarResponse {
        return try {
            val car = carService.getCar(carId)

            CarResponse(car.id, car.manufacturer.name, car.model, car.rentPerKilo, true, null)
        } catch (ex: EntityNotFoundException) {
            CarResponse(null, null, null, null, false, ex.message)
        }
    }
}