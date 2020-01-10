package koltin.collections.folding

data class Shop(val name: String, val customers: List<Customer>)

data class City(val name: String)

data class Customer(val name: String, val city: City, val orders: List<Order>)

data class Order(val products: List<Product>, val isDelivered: Boolean)

data class Product(val name: String, val price: Double)

// Return the set of products that were ordered by every customer
fun Shop.getSetOfProductsOrderedByEveryCustomer(): Set<Product> {
    val toSet = customers.flatMap { it.orders.flatMap { it.products } }.toSet()

    return customers.fold(toSet, {
            orderedByAll, customer ->
        orderedByAll.intersect(customer.orders.flatMap { it.products }.toSet())
    })

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

    println(s.getSetOfProductsOrderedByEveryCustomer())

    println("Done.")
}