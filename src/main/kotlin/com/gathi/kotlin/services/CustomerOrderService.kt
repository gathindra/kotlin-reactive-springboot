package com.gathi.kotlin.services

import com.gathi.kotlin.data.dtos.*
import com.gathi.kotlin.data.mappers.*
import com.gathi.kotlin.repositories.*
import kotlinx.coroutines.flow.*
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class CustomerOrderService @Autowired constructor(
    private val customerRepository: CustomerRepository,
    private val addressRepository: AddressRepository,
    private val orderRepository: OrderRepository,
    private val productRepository: ProductRepository,
    private val orderItemRepository: OrderItemRepository
) {

    private val logger = LoggerFactory.getLogger(this::class.java)

    suspend fun getAllCustomers(): Flow<CustomerDto> = customerRepository
        .findAll()
        .map { entity ->
            logger.debug("Got customer {}", entity)
            entity.toCustomerDto()
        }

    suspend fun findCustomerById(id: Int): CustomerDto =
        customerRepository.findById(id)?.toCustomerDto()
            ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)

    suspend fun saveCustomer(customer: CustomerDto): CustomerDto =
        customerRepository.save(customer.toCustomerEntity())
            .toCustomerDto()

    suspend fun deleteCustomer(id: Int) {
        customerRepository.findById(id)?.let {
            logger.debug("Deleting customer {}", it)
            customerRepository.deleteById(id)
        } ?: throw ResponseStatusException(HttpStatus.NO_CONTENT)
    }


    suspend fun getCustomersAndAssociatedAddresses(): Flow<CustomerDto> =
        customerRepository.findAllCustomersAndAssociatedAddresses()

    suspend fun saveAddress(address: AddressDto): AddressDto =
        addressRepository.save(address.toAddressEntity()).toAddressDto()

    suspend fun findAddressByCustomerId(customerId: Int): Flow<AddressDto> =
        addressRepository.findAddressesByCustomerId(customerId).map { entity ->
            logger.debug("Found address {} for customer id {}", entity, entity.customerId)
            entity.toAddressDto()
        }

    suspend fun createOrder(order: OrderDto): OrderDto =
        orderRepository.save(order.toOrderEntity()).toOrderItemDto()

    suspend fun createOrderItem(orderItem: OrderItemDto): OrderItemDto {
        // Get the product price
        val productPrice = productRepository.findById(orderItem.productId)?.price
            ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
        logger.debug("Price {} for product id {}", productPrice, orderItem.productId)
        // Save the order item with the product price depending on the quantity
        val orderItem = orderItemRepository
            .save(orderItem.copy(price = (productPrice * orderItem.quantity)).toOrderItemEntity())
            .toOrderItemDto()

        logger.debug("Saved order item {}", orderItem)

        // Update the associated order total price
        val updatedOrder = orderRepository.updateOrderTotalByOrderId(orderItem.orderId, orderItem.price)
        logger.debug("Updated order {}", updatedOrder)
        return orderItem
    }


    suspend fun createOrderItems(orderItems: List<OrderItemDto>): Flow<OrderItemDto> =
        orderItemRepository
            .saveAll(
                orderItems
                    .map { dto ->
                        // Get the product price
                        val productPrice = productRepository.findById(dto.productId!!)?.price
                            ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
                        // Update the each order item price depending on the quantity
                        dto.copy(price = (productPrice * dto.quantity)).toOrderItemEntity()
                    }
                    .toList()
            )
            .map { entity ->
                // Update the associated order total price
                orderRepository.updateOrderTotalByOrderId(entity.orderId, entity.price)
                entity.toOrderItemDto()
            }

    suspend fun saveProduct(product: ProductDto): ProductDto =
        productRepository.save(product.toProductEntity()).toProductDto()

    suspend fun saveProducts(products: List<ProductDto>): Flow<ProductDto> =
        productRepository.saveAll(
            products
                .map { dto -> dto.toProductEntity() }
                .toList()
        )
            .map { entity -> entity.toProductDto() }

    fun findAllProducts(): Flow<ProductDto> =
        productRepository.findAll().map { entity -> entity.toProductDto() }

    suspend fun findAllOrders(): Flow<OrderDto> =
        orderRepository.findAll().map { entity -> entity.toOrderItemDto() }

    suspend fun findAllOrdersByCustomerId(customerId: Int): Flow<OrderDto> =
        orderRepository.findOrdersByCustomerId(customerId).map { entity -> entity.toOrderItemDto() }

    suspend fun findAllOrderItemsByOrderId(orderId: Int): Flow<OrderItemDto> =
        orderItemRepository.findOrderItemsByOrderId(orderId).map { entity ->
            entity.toOrderItemDto()
        }


}




