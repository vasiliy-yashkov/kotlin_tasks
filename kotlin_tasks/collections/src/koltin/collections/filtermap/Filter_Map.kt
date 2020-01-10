package koltin.collections.filtermap

import koltin.collections.flatmap.Product

data class Shop(val name: String, val customers: List<Customer>)

data class Order(val products: List<Product>, val isDelivered: Boolean)

data class Customer(val name: String, val city: City, val orders: List<Order>)

data class City(val name: String)

// Return the set of cities the customers are from
fun Shop.getCitiesCustomersAreFrom(): Set<City> = customers.map { it -> it.city }.toSet()

// Return a list of the customers who live in the given city
fun Shop.getCustomersFrom(city: City): List<Customer> = customers.filter { it -> it.city == city }.toList()

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

//    println(s)

    println(s.getCitiesCustomersAreFrom())
    println(s.getCustomersFrom(City("Test City")))

    println("Done.")
}
