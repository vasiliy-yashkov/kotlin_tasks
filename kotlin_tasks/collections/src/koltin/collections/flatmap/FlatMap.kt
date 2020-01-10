package koltin.collections.flatmap

data class Shop(val name: String, val customers: List<Customer>)

data class City(val name: String)

data class Customer(val name: String, val city: City, val orders: List<Order>)

data class Order(val products: List<Product>, val isDelivered: Boolean)

data class Product(val name: String, val price: Double)

// Return all products this customer has ordered
fun Customer.getOrderedProducts(): Set<Product> = orders.flatMap { order -> order.products }.toSet()

// Return all products that were ordered by at least one customer
fun Shop.getAllOrderedProducts() :Set<Product> = customers.flatMap { customer -> customer.getOrderedProducts()}.toSet()

fun main(args: Array<String>) {
    println("Execute...")

    val listOf = listOf(
        Customer(
            "Test Name",
            City("Test City"),
            listOf(Order(listOf(Product("Test Product", 42.42)), false))
        )
    )

    val c = Customer("Test Name", City("Test City"),
        listOf(Order(listOf(Product("Test Product", 42.42)), false)))
    val s = Shop("Test Shop", listOf)

    println(c)

    println(s)

    println(c.getOrderedProducts())
    println(s.getAllOrderedProducts())


    println("Done.")
}
