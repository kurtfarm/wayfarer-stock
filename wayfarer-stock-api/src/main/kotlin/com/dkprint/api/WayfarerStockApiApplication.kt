package com.dkprint.api

import com.dkprint.app.WayfarerStockAppApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
@EntityScan("com.dkprint.app")
@EnableJpaRepositories("com.dkprint.app")
@ComponentScan(basePackageClasses = [WayfarerStockAppApplication::class, WayfarerStockApiApplication::class])
class WayfarerStockApiApplication

fun main(args: Array<String>) {
    runApplication<WayfarerStockApiApplication>(*args)
}
