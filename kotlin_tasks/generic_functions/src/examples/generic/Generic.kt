package examples.generic

import java.util.*

fun <E, C: MutableCollection<E>> Collection<E>.partitionTo(left: C, right: C, function: (E) -> Boolean): Pair<C,C> {
    this.forEach {
            it ->
        if(function(it))    left.add(it)
        else               right.add(it)
    }
    return Pair(left, right)
}

fun partitionWordsAndLines() {
    val (words, lines) = listOf("a", "a b", "c", "d e").
        partitionTo(ArrayList<String>(), ArrayList()) { s -> !s.contains(" ") }
    words == listOf("a", "c")
    lines == listOf("a b", "d e")
}

fun partitionLettersAndOtherSymbols() {
    val (letters, other) = setOf('a', '%', 'r', '}').
        partitionTo(HashSet<Char>(), HashSet()) { c -> c in 'a'..'z' || c in 'A'..'Z'}
    letters == setOf('a', 'r')
    other == setOf('%', '}')
}

fun main(args: Array<String>) {
    println("Execute...")

    println(partitionWordsAndLines())
    println(partitionLettersAndOtherSymbols())

    println("Done.")
}