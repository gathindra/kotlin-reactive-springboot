package com.gathi.kotlin.repositories

import com.gathi.kotlin.data.dtos.CustomerDto
import com.gathi.kotlin.data.enteties.Customer
import kotlinx.coroutines.flow.Flow
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface CustomerRepository: CoroutineCrudRepository<Customer, Int> {

    @Query("""
        SELECT * FROM customers c
        INNER JOIN addresses a on c.customer_id = a.customer_id
    """)
    suspend fun findAllCustomersAndAssociatedAddresses(): Flow<CustomerDto>

}