package koltin.collections.minmax

data class Shop(val name: String, val customers: List<Customer>)

data class City(val name: String)

data class Customer(val name: String, val city: City, val orders: List<Order>)

data class Order(val products: List<Product>, val isDelivered: Boolean)

data class Product(val name: String, val price: Double)

// Return a customer whose order count is the highest among all customers
fun Shop.getCustomerWithMaximumNumberOfOrders(): Customer? = customers.maxBy { it.orders.size }

// Return the most expensive product which has been ordered
fun Customer.getMostExpensiveOrderedProduct(): Product? = orders.flatMap { it.products }.maxBy { it.price }

fun main(args: Array<String>) {
    println("Execute...")

    val listOf = listOf(
        Customer(
            "Test Name",
            City("Test City"),
            listOf(
                Order(
                    listOf(Product("Test Product", 42.42)),
                    false
                )
            )
        )
    )

    val c = Customer(
        "Test Name", City("Test City"),
        listOf(
            Order(
                listOf(Product("Test Product", 42.42)),
                false
            )
        )
    )
    val s = Shop("Test Shop", listOf)

    println(c)

    println(s)

    println(c.getMostExpensiveOrderedProduct())
    println(s.getCustomerWithMaximumNumberOfOrders())


    println("Done.")
}