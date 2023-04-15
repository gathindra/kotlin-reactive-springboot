package com.gathi.kotlin.repositories

import com.gathi.kotlin.data.enteties.OrderItem
import kotlinx.coroutines.flow.Flow
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface OrderItemRepository: CoroutineCrudRepository<OrderItem, Int> {

    @Query(
        """
            SELECT * FROM order_items WHERE order_id = :orderId
        """
    )
    suspend fun findOrderItemsByOrderId(orderId: Int): Flow<OrderItem>
}