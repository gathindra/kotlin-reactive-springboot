package com.gathi.kotlin.data.dtos

data class OrderItemDto (
    val id: Int?,
    val orderId: Int,
    val productId: Int,
    val quantity: Int,
    val price: Float
)
