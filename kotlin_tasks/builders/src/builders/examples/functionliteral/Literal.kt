package builders.examples.functionliteral

fun task(): List<Boolean> {
    val isEven: Int.() -> Boolean = { this % 2 == 0 }
    val isOdd: Int.() -> Boolean = { this % 2 == 1  }

    return listOf(42.isOdd(), 239.isOdd(), 294823098.isEven())
}

fun main(args: Array<String>) {
    println("Execute...")

    println(task())

    println("Done.")
}