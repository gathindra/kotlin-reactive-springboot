package com.gathi.kotlin.repositories

import com.gathi.kotlin.data.enteties.Address
import kotlinx.coroutines.flow.Flow
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository


@Repository
interface AddressRepository: CoroutineCrudRepository<Address, Int> {

    suspend fun findAddressesByCustomerId(customerId: Int): Flow<Address>
}
