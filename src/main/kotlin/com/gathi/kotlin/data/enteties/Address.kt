package com.gathi.kotlin.data.enteties

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table(value = "addresses")
data class Address(
    @Id
    @Column("address_id")
    val id: Int? = null,
    @Column("customer_id")
    val customerId: Int?,
    @Column("street")
    val street: String,
    @Column("city")
    val city: String,
    @Column("state")
    val state: String,
    @Column("country")
    val country: String,
    @Column("zip_code")
    val zipCode: String

)