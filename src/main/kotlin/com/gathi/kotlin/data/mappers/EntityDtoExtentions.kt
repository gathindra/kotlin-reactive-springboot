package com.gathi.kotlin.data.mappers

import com.gathi.kotlin.data.dtos.*
import com.gathi.kotlin.data.enteties.*

fun Customer.toCustomerDto(): CustomerDto = CustomerDto(
    id = this.id,
    firstName = this.firstName,
    lastName = this.lastName,
    email = this.email,
    phone = this.phone
)

fun CustomerDto.toCustomerEntity(): Customer = Customer(
    id = this.id,
    firstName = this.firstName,
    lastName = this.lastName,
    email = this.email,
    phone = this.phone
)

fun Address.toAddressDto(): AddressDto = AddressDto(
    id = this.id,
    customerId = this.customerId,
    street = this.street,
    city = this.city,
    state = this.state,
    country = this.country,
    zipCode = this.zipCode
)

fun AddressDto.toAddressEntity(): Address = Address(
    id = this.id,
    customerId = this.customerId,
    street = this.street,
    city = this.city,
    state = this.state,
    country = this.country,
    zipCode = this.zipCode
)

fun Order.toOrderItemDto(): OrderDto = OrderDto(
    id = this.id,
    customerId = this.customerId,
    orderDate = this.orderDate,
    totalAmount = this.totalAmount,
    orderItems = listOf()
)

fun OrderDto.toOrderEntity(): Order = Order(
    id = this.id,
    customerId = this.customerId,
    orderDate = this.orderDate,
    totalAmount = this.totalAmount
)

fun OrderItem.toOrderItemDto(): OrderItemDto = OrderItemDto(
    id = this.id,
    orderId = this.orderId,
    productId = this.productId,
    quantity = this.quantity,
    price = this.price
)

fun OrderItemDto.toOrderItemEntity(): OrderItem = OrderItem(
    id = this.id,
    orderId = this.orderId,
    productId = this.productId,
    quantity = this.quantity,
    price = this.price
)

fun ProductDto.toProductEntity(): Product = Product(
    id = this.id,
    productName = this.productName,
    description = this.description,
    price = this.price
)

fun Product.toProductDto(): ProductDto = ProductDto(
    id = this.id,
    productName = this.productName,
    description = this.description,
    price = this.price
)