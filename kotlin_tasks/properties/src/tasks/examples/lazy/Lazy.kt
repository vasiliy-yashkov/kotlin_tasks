package tasks.examples.lazy


class LazyProperty(val initializer: () -> Int) {
    val mem by lazy(initializer)

    val lazy: Int
        get() {
            return mem
        }
}

fun init(): Int {
    return 42
}

fun main(args: Array<String>) {
    println("Execute...")

    var test = LazyProperty({ init() });

    println(test.lazy)

    println("Done.")
}