package com.gathi.kotlin.data.enteties

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("order_items")
data class OrderItem(
    @Id
    @Column("order_item_id")
    val id: Int? = null,
    @Column("order_id")
    val orderId: Int,
    @Column("product_id")
    val productId: Int,
    @Column("quantity")
    val quantity: Int,
    @Column("price")
    val price: Float
)
