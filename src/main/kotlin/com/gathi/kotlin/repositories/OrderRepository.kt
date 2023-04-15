package com.gathi.kotlin.repositories

import com.gathi.kotlin.data.enteties.Order
import kotlinx.coroutines.flow.Flow
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface OrderRepository: CoroutineCrudRepository<Order, Int> {

    suspend fun findOrdersByCustomerId(customerId: Int): Flow<Order>

    @Query("""
        UPDATE orders
        SET total_amount = COALESCE(total_amount, 0) + :price
        where order_id = :orderId;
    """)
    suspend fun updateOrderTotalByOrderId(orderId: Int, price: Float): Order

}