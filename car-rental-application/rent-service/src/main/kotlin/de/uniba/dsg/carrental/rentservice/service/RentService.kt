package de.uniba.dsg.carrental.rentservice.service

import com.google.gson.Gson
import de.uniba.dsg.carrental.rentservice.exception.BadRequestException
import de.uniba.dsg.carrental.rentservice.model.dto.CarRequest
import de.uniba.dsg.carrental.rentservice.model.dto.CarResponse
import de.uniba.dsg.carrental.rentservice.model.dto.DistanceRequest
import de.uniba.dsg.carrental.rentservice.model.dto.DistanceResponse
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import kotlin.jvm.Throws

@Service
class RentService(private val rabbitTemplate: RabbitTemplate, private val gson: Gson) {

    @Value("\${message-service.distanceQueue}")
    var distanceQueueName: String = String()

    @Value("\${message-service.car_rent_price_queue}")
    var carRentPriceQueueName: String = String()

    @Throws(BadRequestException::class)
    fun calculateRent(from: String, to: String, carId: Long): Double {
        val carResponse = gson.fromJson(
            rabbitTemplate.convertSendAndReceive(carRentPriceQueueName, gson.toJson(CarRequest(carId, from))) as String,
            CarResponse::class.java
        )

        if (!carResponse.isSuccessful)
            throw carResponse.errorMessage?.let { BadRequestException(it) }!!

        val distanceResponse = gson.fromJson(
            rabbitTemplate.convertSendAndReceive(distanceQueueName, gson.toJson(DistanceRequest(from, to))) as String,
            DistanceResponse::class.java
        )

        if (!distanceResponse.isSuccessful)
            throw distanceResponse.errorMessage?.let { BadRequestException(it) }!!

        return (carResponse.rentPerKilo * distanceResponse.distance)
    }
}