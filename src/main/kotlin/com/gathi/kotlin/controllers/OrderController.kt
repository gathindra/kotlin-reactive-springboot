package com.gathi.kotlin.controllers

import com.gathi.kotlin.data.dtos.OrderDto
import com.gathi.kotlin.data.dtos.OrderItemDto
import com.gathi.kotlin.services.CustomerOrderService
import kotlinx.coroutines.flow.Flow
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/reactive/order")
class OrderController @Autowired constructor(
    private val orderService: CustomerOrderService
) {

    @GetMapping("/all")
    suspend fun findAllOrders(): Flow<OrderDto> =
        orderService.findAllOrders()

    @PostMapping("/create")
    suspend fun createOrder(@RequestBody order: OrderDto): OrderDto {
        return orderService.createOrder(order)
    }

    @PostMapping("/order-item/create")
    suspend fun createOrderItems(@RequestBody orderItem: OrderItemDto): OrderItemDto =
        orderService.createOrderItem(orderItem)

    @PostMapping("/order-items/create")
    suspend fun createOrderItems(@RequestBody orderItems: List<OrderItemDto>): Flow<OrderItemDto> {
        return orderService.createOrderItems(orderItems)
    }

    @GetMapping("/order-items/all/by/{orderId}")
    suspend fun findAllOrderItemsByOrderId(@PathVariable orderId: Int): Flow<OrderItemDto> =
        orderService.findAllOrderItemsByOrderId(orderId)
}