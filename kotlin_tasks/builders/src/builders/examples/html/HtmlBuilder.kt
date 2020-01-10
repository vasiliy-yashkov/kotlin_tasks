package builders.examples.html

data class Product(val description: String, val price: Double, val popularity: Int)

open class Tag(val name: String)

class Attribute(val name : String, val value : String)

fun <T: Tag> T.set(name: String, value: String?): T = TODO()

fun <T: Tag> Tag.doInit(tag: T, init: T.() -> Unit): T = TODO()

class Html: Tag("html")
class Table: Tag("table")
class Center: Tag("center")
class TR: Tag("tr")
class TD: Tag("td")
class Text(val text: String): Tag("b")

fun html(init: Html.() -> Unit): Html {
    val v = Html()
    v.init()
    return v
}

fun Html.table(init : Table.() -> Unit): Table {
    val t = Table()
    t.init()
    return t
}

fun Html.center(init : Center.() -> Unit): Center {
    return doInit(Center(), init)
}

fun Table.tr(color: String? = null, init : TR.() -> Unit): TR {
    return doInit(TR(), init)
}

fun TR.td(color: String? = null, align : String = "left", init : TD.() -> Unit): TD = TODO()

fun Tag.text(s : Any?): Text = TODO()

fun renderProductTable(): String {
    return html {
        table {
            tr {
                td (getTitleColor(), "left", {
                    text("Price")
                })
                this.td (getTitleColor()){
                    text("Price")
                }
                td(getTitleColor()) {
                    text("Popularity")
                }
            }
            val products = getProducts()
            products.forEachIndexed  {
                    idx, it ->
                tr(getCellColor(idx, 1))  {
                    td {
                        text(it.description)
                    }
                    td {
                        text(it.price)
                    }
                    td {
                        text(it.popularity)
                    }
                }
            }

        }
    }.toString()
}

fun getProducts(): List<Product> {
    return listOf(Product("Test", 34.3, 10), Product("Test1", 34.3, 10));
}

fun getTitleColor() = "#b9c9fe"
fun getCellColor(index: Int, row: Int) = if ((index + row) %2 == 0) "#dce4ff" else "#eff2ff"

fun main(args: Array<String>) {
    println("Execute...")

    println(renderProductTable())

    println("Done.")
}