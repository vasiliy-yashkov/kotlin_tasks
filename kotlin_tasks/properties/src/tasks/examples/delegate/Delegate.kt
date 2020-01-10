package tasks.examples.delegate

private class LazyProperty(val initializer: () -> Int) {
    val lazyValue: Int by lazy(initializer)
}


private fun init(): Int {
    return 42
}

fun main(args: Array<String>) {
    println("Execute...")

    var test = LazyProperty({ init() });

    println(test.lazyValue)

    println("Done.")
}