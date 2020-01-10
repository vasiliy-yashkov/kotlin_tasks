package koltin.collections.allany

import koltin.collections.flatmap.Product

data class Shop(val name: String, val customers: List<Customer>)

data class City(val name: String)

data class Order(val products: List<Product>, val isDelivered: Boolean)

data class Customer(val name: String, val city: City, val orders: List<Order>)

// Return true if all customers are from the given city
fun Shop.checkAllCustomersAreFrom(city: City): Boolean = customers.all { it.city == city }

// Return true if there is at least one customer from the given city
fun Shop.hasCustomerFrom(city: City): Boolean = customers.any { it.city == city }

// Return the number of customers from the given city
fun Shop.countCustomersFrom(city: City): Int = customers.count { it.city == city }

// Return a customer who lives in the given city, or null if there is none
fun Shop.findAnyCustomerFrom(city: City): Customer? = customers.find { it.city == city }

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

    println(s.checkAllCustomersAreFrom(City("Test City")))
    println(s.hasCustomerFrom(City("Test City")))
    println(s.countCustomersFrom(City("Test City")))
    println(s.findAnyCustomerFrom(City("Test City Null")))

    println("Done.")
}
