package com.gathi.kotlin.data.dtos

data class AddressDto (
    val id: Int?,
    val customerId: Int?,
    val street: String,
    val city: String,
    val state: String,
    val country: String,
    val zipCode: String
)
