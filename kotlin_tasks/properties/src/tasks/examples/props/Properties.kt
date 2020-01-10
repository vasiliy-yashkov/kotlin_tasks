package tasks.examples.props

class PropertyExample() {
    var counter = 0
    var propertyWithCounter: Int? = null
        set(value) {
            counter++
            field = value
        }


}

fun main(args: Array<String>) {
    println("Execute...")

    var test = PropertyExample();

    test.propertyWithCounter = 23
    test.propertyWithCounter = 34

    println(test.counter)

    println("Done.")
}