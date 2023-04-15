package com.gathi.kotlin.controllers

import com.gathi.kotlin.data.dtos.AddressDto
import com.gathi.kotlin.data.dtos.CustomerDto
import com.gathi.kotlin.data.enteties.Address
import com.gathi.kotlin.services.CustomerOrderService
import kotlinx.coroutines.flow.Flow
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/reactive/customer")
class CustomerController @Autowired constructor(val service: CustomerOrderService) {

    @GetMapping("/all")
    suspend fun customers(): Flow<CustomerDto> = service.getAllCustomers()

    @GetMapping("/all/with-address")
    suspend fun getCustomersAndAddresses(): Flow<CustomerDto> =
        service.getCustomersAndAssociatedAddresses()

    @PostMapping("/save")
    suspend fun saveCustomer(@RequestBody customer: CustomerDto): CustomerDto = service.saveCustomer(customer)

    @DeleteMapping("/delete/{id}")
    suspend fun deleteCustomer(@PathVariable id: Int) = service.deleteCustomer(id)

    @PostMapping("/address/save")
    suspend fun saveAddress(@RequestBody address: AddressDto): AddressDto = service.saveAddress(address)

    @GetMapping("/address/by/customer/{customer-id}")
    suspend fun getAddressForCustomer(@PathVariable("customer-id") customerId: Int): Flow<AddressDto> =
        service.findAddressByCustomerId(customerId)
}