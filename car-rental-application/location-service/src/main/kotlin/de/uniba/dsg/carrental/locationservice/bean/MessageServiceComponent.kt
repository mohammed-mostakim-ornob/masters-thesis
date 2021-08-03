package de.uniba.dsg.carrental.locationservice.bean

import de.uniba.dsg.carrental.locationservice.exception.EntityNotFoundException
import de.uniba.dsg.carrental.locationservice.model.dto.DistanceRequest
import de.uniba.dsg.carrental.locationservice.model.dto.DistanceResponse
import de.uniba.dsg.carrental.locationservice.service.DistanceService
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Component

@Component
class MessageServiceComponent(private val distanceService: DistanceService) {

    @RabbitListener(queues = ["\${message-service.distanceQueue}"])
    fun listen(distanceRequest: DistanceRequest): DistanceResponse {
        return try {
            val distance = distanceService.getLocationByFromAndToCode(distanceRequest.from, distanceRequest.to)

            DistanceResponse(distance.from.code, distance.to.code, distance.distance, true, null)
        } catch (ex: EntityNotFoundException) {
            DistanceResponse(distanceRequest.from, distanceRequest.from, Double.NaN, false, ex.message)
        }
    }
}