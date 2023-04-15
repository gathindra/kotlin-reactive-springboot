package com.gathi.kotlin.data.enteties

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

// https://www.sipios.com/blog-tech/handle-the-new-r2dbc-specification-in-java

@Table(value = "customers")
data class Customer(
    @Id
    @Column("customer_id")
    var id: Int? = null,
    @Column("first_name")
    var firstName: String,
    @Column("last_name")
    var lastName: String,
    @Column("email")
    var email: String,
    @Column("phone")
    var phone: String?
)


