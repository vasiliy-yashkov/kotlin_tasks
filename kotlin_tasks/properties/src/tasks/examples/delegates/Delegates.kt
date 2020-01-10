package tasks.examples.delegates

import java.sql.Date
import java.time.LocalDate
import java.util.*
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class D {
    var date: MyDate by EffectiveDate()
}

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int) : Comparable<MyDate> {

    override fun compareTo(other: MyDate): Int {
        val d1 = LocalDate.of(other.year, other.month, other.dayOfMonth)

        val d2 = LocalDate.of(this.year, this.month, this.dayOfMonth)

        return d2.compareTo(d1)
    }

    fun toMillis(): Long {

        val d2 = Date.valueOf(LocalDate.of(this.year, this.month + 1, this.dayOfMonth)).time

        return d2
    }
}
fun Long.toDate(): MyDate {

    val c = Calendar.getInstance()
    c.timeInMillis = this
    return MyDate(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DATE))
}


class EffectiveDate<R> : ReadWriteProperty<R, MyDate> {

    var timeInMillis: Long? = null

    override fun getValue(thisRef: R, property: KProperty<*>): MyDate {
        val c = Calendar.getInstance()
        c.timeInMillis = timeInMillis ?: 0
        return MyDate(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH))
    }

    override fun setValue(thisRef: R, property: KProperty<*>, value: MyDate) {
        timeInMillis = value.toMillis()
    }
}

fun main(args: Array<String>) {
    println("Execute...")

    var test = D()

    println(test.date.toMillis().toDate())

    println("Done.")
}