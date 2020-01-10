package koltin.collections.compound

data class Shop(val name: String, val customers: List<Customer>)

data class City(val name: String)

data class Customer(val name: String, val city: City, val orders: List<Order>)

data class Order(val products: List<Product>, val isDelivered: Boolean)

data class Product(val name: String, val price: Double)

// Return the most expensive product among all delivered products
// (use the Order.isDelivered flag)
fun Customer.getMostExpensiveDeliveredProduct(): Product? {
    val(del, undel) = orders.partition { it.isDelivered }
    return del.flatMap { it.products }.maxBy { it.price }
}

// Return how many times the given product was ordered.
// Note: a customer may order the same product for several times.
fun Shop.getNumberOfTimesProductWasOrdered(product: Product): Int {
    val flatMap = customers.flatMap { it.orders }.flatMap { it.products }
    return flatMap.count { it == product }
}

fun doSomethingStrangeWithCollection(collection: Collection<String>): Collection<String>? {

    val groupsByLength = collection.groupBy { s -> s.length }

    val maximumSizeOfGroup = groupsByLength.values.map { group -> group.size }.max()

    return groupsByLength.values.firstOrNull { group -> group.size == maximumSizeOfGroup }
}

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

    println(s.getNumberOfTimesProductWasOrdered(Product("Test Product", 42.42)))

    println(c.getMostExpensiveDeliveredProduct())

    println(doSomethingStrangeWithCollection(listOf("a", "bb", "Ccc", "dd")))

    println("Done.")
}

