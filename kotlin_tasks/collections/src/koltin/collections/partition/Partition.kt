package koltin.collections.partition

data class Shop(val name: String, val customers: List<Customer>)

data class City(val name: String)

data class Customer(val name: String, val city: City, val orders: List<Order>)

data class Order(val products: List<Product>, val isDelivered: Boolean)

data class Product(val name: String, val price: Double)

// Return customers who have more undelivered orders than delivered
fun Shop.getCustomersWithMoreUndeliveredOrdersThanDelivered(): Set<Customer> = customers.filter {
    val (del, undel) = it.orders.partition { it.isDelivered }
    undel.size > del.size
}.toSet()


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
        ),
        Customer(
            "Test Name 2",
            City("Test City 2"),
            listOf(
                Order(
                    listOf(Product("Test Product", 42.42),
                        Product("Test Product", 42.42),
                        Product("Test Product 3", 42.42),
                        Product("Test Product 4", 42.42),
                        Product("Test Product", 42.42)),
                    false
                )
            )
        ),
        Customer(
            "Test Name 3",
            City("Test City 3"),
            listOf(
                Order(
                    listOf(Product("Test Product", 42.42),
                        Product("Test Product", 42.42),
                        Product("Test Product", 42.42)),
                    false
                )
            )
        )
    )

    val c = Customer(
        "Test Name", City("Test City"),
        listOf(
            Order(
                listOf(Product("Test Product", 42.42),
                    Product("Test Product", 42.42),
                    Product("Test Product 3", 42.42),
                    Product("Test Product 4", 42.42),
                    Product("Test Product", 42.42)),
                false
            )
        )
    )
    val s = Shop("Test Shop", listOf)

    println(c)

    println(s)

    println(s.getCustomersWithMoreUndeliveredOrdersThanDelivered())

    println("Done.")
}