package builders.examples.stringmap

import java.util.HashMap

fun buildString(build: StringBuilder.() -> Unit): String {
    val stringBuilder = StringBuilder()
    stringBuilder.build()
    return stringBuilder.toString()
}

val s = buildString {
    this.append("Numbers: ")
    for (i in 1..3) {
        // 'this' can be omitted
        append(i)
    }
}

fun buildMap(build: HashMap<Int, String>.() -> Unit): Map<Int, String> {
    val hm = HashMap<Int, String>()
    hm.build()
    return hm
}

fun usage(): Map<Int, String> {
    return buildMap {
        put(0, "0")
        for (i in 1..10) {
            put(i, "$i")
        }
    }
}

fun main(args: Array<String>) {
    println("Execute...")

    val u = usage()

    println(u)

    println("Done.")
}