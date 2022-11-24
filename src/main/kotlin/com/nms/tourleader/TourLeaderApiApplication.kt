package com.nms.tourleader

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
class TourLeaderApiApplication

fun main(args: Array<String>) {
	runApplication<TourLeaderApiApplication>(*args)
}
