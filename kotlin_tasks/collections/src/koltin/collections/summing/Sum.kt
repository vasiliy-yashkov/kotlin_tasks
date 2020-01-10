package koltin.collections.summing

data class Shop(val name: String, val customers: List<Customer>)

data class City(val name: String)

data class Customer(val name: String, val city: City, val orders: List<Order>)

data class Order(val products: List<Product>, val isDelivered: Boolean)

data class Product(val name: String, val price: Double)

// Return the sum of prices of all products that a customer has ordered.
// Note: the customer may order the same product for several times.
fun Customer.getTotalOrderPrice(): Double = orders.sumByDouble { order -> order.products.sumByDouble { product -> product.price } }

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

    println(c.getTotalOrderPrice())

    println("Done.")
}