package com.gathi.kotlin

import com.gathi.kotlin.repositories.AddressRepository
import com.gathi.kotlin.repositories.CustomerRepository
import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Info
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories

@SpringBootApplication
@EnableR2dbcRepositories
@OpenAPIDefinition(
    info = Info(
        title = "Reactive Springboot with Kotlin",
        version = "1.0.0",
        description = "Documentation for the reactive springboot application endpoints"
    )
)
class KotlinReactiveSpringbootApplication

fun main(args: Array<String>) {
    runApplication<KotlinReactiveSpringbootApplication>(*args)
}

/*
@Configuration
class AppConfig {
    @Bean
    fun applicationRunner(
        customerRepository: CustomerRepository,
        addressRepository: AddressRepository
    ) = ApplicationRunner {
        runBlocking {
            val customers = customerRepository.findAll().toList()
            println(customers)
            val customers2 = customerRepository.findAllCustomersAndAssociatedAddresses().toList()
            println(customers2)

            val addresses = addressRepository.findAddressesByCustomerId(1)
            println(addresses.toList())
        }

    }
}
*/
