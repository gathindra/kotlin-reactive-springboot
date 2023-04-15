package com.gathi.kotlin.data.enteties

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("products")
data class Product(
    @Id
    @Column("product_id")
    val id: Int? = null,
    @Column("product_name")
    val productName: String,
    @Column("description")
    val description: String,
    @Column("price")
    val price: Float
)
