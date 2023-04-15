package com.gathi.kotlin.repositories

import com.gathi.kotlin.data.enteties.Product
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository: CoroutineCrudRepository<Product, Int> {
}