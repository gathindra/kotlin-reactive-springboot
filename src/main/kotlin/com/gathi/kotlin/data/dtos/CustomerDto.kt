package com.gathi.kotlin.data.dtos

data class CustomerDto(
    val id: Int?,
    val firstName: String,
    val lastName: String,
    val email: String,
    val phone: String?,
    var addresses: MutableList<AddressDto> = mutableListOf()
) {
}