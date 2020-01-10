package koltin.collections.introduction

import koltin.collections.flatmap.Product

data class Shop(val name: String, val customers: List<Customer>)

data class Order(val products: List<Product>, val isDelivered: Boolean)

data class Customer(val name: String, val city: City, val orders: List<Order>)

data class City (val name: String)

fun Shop.getSetOfCustomers(): Set<Customer> = customers.toSet()

fun main(args: Array<String>) {
    println("Execute...")

    val listOf = listOf(
        Customer(
            "Test Name",
            City("Test City"),
            listOf(Order(listOf(Product("Test Product", 42.42)), false))
        )
    )

    val s = Shop("Test Shop", listOf)

    println(s)

    println(s.getSetOfCustomers())

    println("Done.")
}
