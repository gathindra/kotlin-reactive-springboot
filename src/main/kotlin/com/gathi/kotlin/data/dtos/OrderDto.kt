package com.gathi.kotlin.data.dtos

import com.fasterxml.jackson.annotation.JsonFormat

import java.time.LocalDateTime

data class OrderDto(
    val id: Int?,
    val customerId: Int?,
    //2023-04-04T01:30:41.145Z
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    var orderDate: LocalDateTime,
    val totalAmount: Float,
    val orderItems: List<OrderItemDto> = listOf()
)
