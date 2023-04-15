package com.gathi.kotlin.data.enteties

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime

@Table("orders")
data class Order(
    @Id
    @Column("order_id")
    val id: Int? = null,
    @Column("customer_id")
    val customerId: Int?,
    @Column("order_date")
    val orderDate: LocalDateTime,
    @Column("total_amount")
    val totalAmount: Float
)
