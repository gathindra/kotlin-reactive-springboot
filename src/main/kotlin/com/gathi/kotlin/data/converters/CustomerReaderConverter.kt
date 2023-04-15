package com.gathi.kotlin.data.converters

import com.gathi.kotlin.data.dtos.AddressDto
import com.gathi.kotlin.data.dtos.CustomerDto
import com.gathi.kotlin.data.enteties.Address
import com.gathi.kotlin.data.enteties.Customer
import io.r2dbc.spi.Row
import org.springframework.core.convert.converter.Converter

import org.springframework.data.convert.ReadingConverter

@ReadingConverter
class CustomerReaderConverter: Converter<Row, CustomerDto> {
    override fun convert(source: Row): CustomerDto {
        var address: AddressDto? = null

        if (source.metadata.contains("address_id")) {
            address = AddressDto(
                id = source.get("address_id", Int::class.javaObjectType),
                customerId = source.get("customer_id", Int::class.javaObjectType),
                street = source.get("street", String::class.java) ?: "",
                city = source.get("city", String::class.java) ?: "",
                state = source.get("state", String::class.java) ?: "",
                country = source.get("country", String::class.java) ?: "",
                zipCode = source.get("zip_code", String::class.java) ?: "",
            )
        }

        val customer = CustomerDto(
            id = source.get("customer_id", Int::class.javaObjectType),
            firstName = source.get("first_name", String::class.java) ?: "",
            lastName = source.get("last_name", String::class.java) ?: "",
            email = source.get("email", String::class.java) ?: "",
            phone = source.get("phone", String::class.java) ?: ""
        )

        if (address != null) {
            customer.addresses.add(address)
        }

        return customer
    }
}