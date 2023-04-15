package com.gathi.kotlin.controllers

import com.gathi.kotlin.data.dtos.ProductDto
import com.gathi.kotlin.services.CustomerOrderService
import kotlinx.coroutines.flow.Flow
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/reactive/product")
class ProductController @Autowired constructor(private val customerOrderService: CustomerOrderService) {

    @GetMapping("/all")
    suspend fun allProducts(): Flow<ProductDto> = customerOrderService.findAllProducts()

    @PostMapping("/save")
    suspend fun saveProduct(@RequestBody product: ProductDto): ProductDto =
        customerOrderService.saveProduct(product)

    @PostMapping("/save-bulk")
    suspend fun saveProducts(@RequestBody products: List<ProductDto>): Flow<ProductDto> =
        customerOrderService.saveProducts(products)
}