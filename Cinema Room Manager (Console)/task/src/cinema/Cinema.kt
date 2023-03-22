package cinema

const val ROW_NUMBER = 7
const val SEAT_NUMBER = 8
const val HEADERS = "Cinema:\n  1 2 3 4 5 6 7 8"
const val ROW_TEMPLATE = "%d %s %s %s %s %s %s %s %s"
val cinema = Array(ROW_NUMBER) { Array(SEAT_NUMBER) { "S" } }

fun main() {
    printCinema()
}

fun printCinema() {
    println(HEADERS)
    cinema.forEachIndexed { i, row ->
        println(ROW_TEMPLATE.format(i + 1, *row))
    }
}
