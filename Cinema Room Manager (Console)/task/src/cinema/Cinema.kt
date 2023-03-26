package cinema


const val ROWS_PROMPT = "Enter the number of rows:"
const val SEATS_PROMPT = "Enter the number of seats in each row:"
const val MENU = "\n1. Show the seats\n2. Buy a ticket\n0. Exit"
const val OCCUPY_ROW_PROMPT = "\nEnter a row number:"
const val OCCUPY_SEAT_PROMPT = "Enter a seat number in that row:"
const val FRONT_PRICE = 10
const val BACK_PRICE = 8
const val SMALL_AUDITORIUM_LIMIT = 60
const val VACANT = 'S'
const val BUSY = 'B'

fun main() {
    val rowNumber = enterNumber(ROWS_PROMPT)
    val seatNumber = enterNumber(SEATS_PROMPT)
    val aud = Array(rowNumber) { Array(seatNumber) { VACANT } }

    while (true) {
        println(MENU)
        when (readln().toInt()) {
            1 -> printAuditorium(aud)
            2 -> buyTicket(aud)
            0 -> break
        }
    }
}

fun buyTicket(aud: Array<Array<Char>>) {
    val busy = Pair(
        enterNumber(OCCUPY_ROW_PROMPT) - 1,
        enterNumber(OCCUPY_SEAT_PROMPT) - 1
    )
    setBusySeat(busy, aud)
    printTicketPrice(getTicketPrice(busy.first, aud.size, aud[0].size))
}

fun enterNumber(prompt: String): Int {
    println(prompt)
    return readln().toInt()
}

fun printAuditorium(aud: Array<Array<Char>>) {
    print("\nCinema:\n  ")
    println((1..aud[0].size).joinToString(" "))
    (1..aud.size).forEach{ println("$it ${aud[it - 1].joinToString(" ")}") }
}

fun getTicketPrice(row: Int, rowNumber: Int, seatNumber: Int): Int {
    return if (rowNumber * seatNumber <= SMALL_AUDITORIUM_LIMIT
        || row + 1 <= rowNumber / 2
    ) FRONT_PRICE else BACK_PRICE
}

fun setBusySeat(busy: Pair<Int, Int>, aud: Array<Array<Char>>) {
    aud[busy.first][busy.second] = BUSY
}

fun printTicketPrice(price: Int) {
    println("Ticket price: \$$price")
}
