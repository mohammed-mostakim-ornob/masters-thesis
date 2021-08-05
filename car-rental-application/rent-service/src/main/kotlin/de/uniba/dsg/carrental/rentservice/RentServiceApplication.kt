package de.uniba.dsg.carrental.rentservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class RentServiceApplication

fun main(args: Array<String>) {
	runApplication<RentServiceApplication>(*args)
}
