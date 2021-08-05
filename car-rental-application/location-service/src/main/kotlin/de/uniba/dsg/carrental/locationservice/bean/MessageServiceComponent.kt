package de.uniba.dsg.carrental.locationservice.bean

import com.google.gson.Gson
import de.uniba.dsg.carrental.locationservice.exception.EntityNotFoundException
import de.uniba.dsg.carrental.locationservice.model.dto.DistanceRequest
import de.uniba.dsg.carrental.locationservice.model.dto.DistanceResponse
import de.uniba.dsg.carrental.locationservice.service.DistanceService
import org.springframework.amqp.core.Queue
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component

@Component
class MessageServiceComponent(private val distanceService: DistanceService, private val gson: Gson) {

    @Value("\${message-service.distanceQueue}")
    var queueName: String = String()

    @Bean
    fun queue(): Queue {
        return Queue(queueName, false)
    }

    @RabbitListener(queues = ["\${message-service.distanceQueue}"])
    fun listen(request: String): String {
        return try {
            val distanceRequest = gson.fromJson(request, DistanceRequest::class.java)

            val distance = distanceService.getLocationByFromAndToCode(distanceRequest.from, distanceRequest.to)

            gson.toJson(DistanceResponse(distance.from.code, distance.to.code, distance.distance, true, null))
        } catch (ex: EntityNotFoundException) {
            gson.toJson(DistanceResponse(null, null, 0.0, false, ex.message))
        }
    }
}