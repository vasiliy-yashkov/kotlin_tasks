import java.time.LocalDate
import java.util.*


fun foo(name: String, number: Int = 42, toUpperCase: Boolean = false) =
    (if (toUpperCase) name.toUpperCase() else name) + number

fun useFoo() = listOf(
    foo("a"),
    foo("b", number = 1),
    foo("c", toUpperCase = true),
    foo(name = "d", number = 2, toUpperCase = true)
)

fun main(args: Array<String>) {
    println(ConvertedJava().toJSON(mutableListOf(10, 20, 30, 40, 50)))
    println(ConvertedJava().joinOptions(mutableListOf("10", "20", "30", "40", "50")))
    println(useFoo())
    println(ConvertedJava().containsEven(mutableListOf(1, 11, 3, 5, 6)))
    println(getPeople())
    println(getList())
    println(getListSortedDescending())
    println(compare(MyDate(year=2014, month=5, dayOfMonth=10), MyDate(year=2014, month=7, dayOfMonth=11)))
    println(task1(MyDate(2019, 12, 9)))
    println(task2(MyDate(2019, 12, 9)))
    println(invokeTwice(Invokable()))
}

class ConvertedJava {
    fun toJSON(collection: Collection<Int?>): String {
        val sb = StringBuilder()
        sb.append("[")
        val iterator = collection.iterator()
        while (iterator.hasNext()) {
            val element = iterator.next()
            sb.append(element)
            if (iterator.hasNext()) {
                sb.append(", ")
            }
        }
        sb.append("]")
        return sb.toString()
    }

    fun joinOptions(options: Collection<String>) = options.joinToString(", ", "[", "]")

    fun containsEven(collection: Collection<Int>): Boolean = collection.any {
        it % 2 == 0
    }

    val month = "(JAN|FEB|MAR|APR|MAY|JUN|JUL|AUG|SEP|OCT|NOV|DEC)"

    fun getPattern(): String = """\d{2}\s(JAN|FEB|MAR|APR|MAY|JUN|JUL|AUG|SEP|OCT|NOV|DEC)\s\d{4}"""
}


data class Person(val name: String, val age: Int)

fun getPeople(): List<Person> {
    return listOf(Person("Alice", 29), Person("Bob", 31))
}

fun sendMessageToClient(
    client: Client?,
    message: String?,
    mailer: Mailer
) {
    if (client == null || message == null) return
    val personalInfo = client.personalInfo ?: return
    val email = personalInfo.email ?: return
    mailer.sendMessage(email, message)
}

class Client (val personalInfo: PersonalInfo?)

class PersonalInfo (val email: String?)
interface Mailer {
    fun sendMessage(email: String, message: String)
}

fun eval(expr: Expr): Int =
    when (expr) {
        is Num -> expr.value
        is Sum -> eval(expr.left) + eval(expr.right)
        else -> throw IllegalArgumentException("Unknown expression")
    }

interface Expr
class Num(val value: Int) : Expr
class Sum(val left: Expr, val right: Expr) : Expr

fun Int.r(): RationalNumber = RationalNumber(this, 1)
fun Pair<Int, Int>.r(): RationalNumber = RationalNumber(this.first, this.second)

data class RationalNumber(val numerator: Int, val denominator: Int)

fun getList(): List<Int> {
    val arrayList = arrayListOf(1, 5, 2)
    Collections.sort(arrayList) { x, y -> y - x }
    return arrayList
}

fun getListSortedDescending(): List<Int> {
    return arrayListOf(1, 5, 2).sortedDescending()
}

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int) : Comparable<MyDate> {
    override fun compareTo(other: MyDate): Int {
        val d1 = LocalDate.of(other.year, other.month, other.dayOfMonth)

        val d2 = LocalDate.of(this.year, this.month, this.dayOfMonth)

        return d2.compareTo(d1)
    }
}

fun compare(date1: MyDate, date2: MyDate) = date1 < date2

class DateRange(val start: MyDate, val endInclusive: MyDate) {
    operator fun contains(d: MyDate): Boolean {
        val d1 = LocalDate.of(start.year, start.month, start.dayOfMonth)

        val d2 = LocalDate.of(endInclusive.year, endInclusive.month, endInclusive.dayOfMonth)

        val d3 = LocalDate.of(d.year, d.month, d.dayOfMonth)

        if (d3 > d1 && d3 < d2)
            return true

        return false
    }
}

fun checkInRange(date: MyDate, first: MyDate, last: MyDate): Boolean {
    return date in DateRange(first, last)
}

operator fun MyDate.rangeTo(other: MyDate): DateRangeC {
    if (other < this) {
        return other.rangeTo(this)
    }
    return DateRangeC(this, other)
}

class DateRangeC(override val start: MyDate, override val endInclusive: MyDate): ClosedRange<MyDate>

fun checkInRangeC(date: MyDate, first: MyDate, last: MyDate): Boolean {
    return date in first..last
}

/*
class DateRangeI(val start: MyDate, val end: MyDate): Iterable<MyDate>{
    override fun iterator(): Iterator<MyDate> = DateIterator(this)
}

class DateIterator(val dateRange:DateRangeI) : Iterator<MyDate> {
    var current: MyDate = dateRange.start

    override fun next(): MyDate {
        val result = current
        current = current.nextDay()
        return result
    }

    override fun hasNext(): Boolean = current <= dateRange.end
}

fun iterateOverDateRange(firstDate: MyDate, secondDate: MyDate, handler: (MyDate) -> Unit) {
    for (date in firstDate..secondDate) {
        handler(date)
    }
}
*/

enum class TimeInterval { DAY, WEEK, YEAR }

fun MyDate.addTimeIntervals(timeInterval: TimeInterval, number: Int): MyDate {
    val c = Calendar.getInstance()
    c.set(year, month, dayOfMonth)
    when (timeInterval) {
        TimeInterval.DAY -> c.add(Calendar.DAY_OF_MONTH, number)
        TimeInterval.WEEK -> c.add(Calendar.WEEK_OF_MONTH, number)
        TimeInterval.YEAR -> c.add(Calendar.YEAR, number)
    }
    return MyDate(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DATE))
}

operator fun MyDate.plus(timeInterval: TimeInterval) = addTimeIntervals(timeInterval, 1)

class RepeatedTimeInterval(val timeInterval: TimeInterval, val number: Int)
operator fun TimeInterval.times(number: Int) = RepeatedTimeInterval(this, number)

operator fun MyDate.plus(timeIntervals: RepeatedTimeInterval) = addTimeIntervals(timeIntervals.timeInterval, timeIntervals.number)

fun task1(today: MyDate): MyDate {
    return today + TimeInterval.YEAR + TimeInterval.WEEK
}

fun task2(today: MyDate): MyDate {
    return today + TimeInterval.YEAR * 2 + TimeInterval.WEEK * 3 + TimeInterval.DAY * 5
}

class Invokable {
    var numberOfInvocations: Int = 0
        private set
    operator fun invoke(): Invokable {
        this.numberOfInvocations++
        return this
    }
}

fun invokeTwice(invokable: Invokable) = invokable()()