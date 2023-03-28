package cinema


const val ROWS_PROMPT = "Enter the number of rows:"
const val SEATS_PROMPT = "Enter the number of seats in each row:"
const val MENU = "\n1. Show the seats\n2. Buy a ticket\n3. Statistics\n0. Exit"
const val OCCUPY_ROW_PROMPT = "\nEnter a row number:"
const val OCCUPY_SEAT_PROMPT = "Enter a seat number in that row:"
const val WRONG_INPUT = "\nWrong input!"
const val SEAT_PURCHASED = "\nThat ticket has already been purchased!"
const val FRONT_PRICE = 10
const val BACK_PRICE = 8
const val SMALL_AUDITORIUM_LIMIT = 60
const val VACANT = 'S'
const val BUSY = 'B'
var currentIncome = 0
var purchasedTickets = 0

fun main() {
    val rowNumber = enterNumber(ROWS_PROMPT)
    val seatNumber = enterNumber(SEATS_PROMPT)
    val totalSeats = rowNumber * seatNumber
    val aud = Array(rowNumber) { Array(seatNumber) { VACANT } }
    val totalIncome = calcTotalIncome(rowNumber, seatNumber)

    while (true) {
        println(MENU)
        when (readln().toInt()) {
            1 -> printAuditorium(aud)
            2 -> buyTicket(aud)
            3 -> printStats(totalSeats, totalIncome)
            0 -> break
        }
    }
}

fun printStats(seats: Int, tIncome: Int) {
    val sPercentage = "%.2f".format(purchasedTickets.toDouble() / seats * 100)
    println()
    println("""
        Number of purchased tickets: $purchasedTickets
        Percentage: $sPercentage%
        Current income: $$currentIncome
        Total income: $$tIncome""".trimIndent())
}

fun calcTotalIncome(rowNumber: Int, seatNumber: Int): Int {
    val totalSeats = rowNumber * seatNumber
    if (totalSeats <= SMALL_AUDITORIUM_LIMIT) {
        return totalSeats * FRONT_PRICE
    }
    val firstRows = rowNumber / 2
    return firstRows * seatNumber * FRONT_PRICE +
            (rowNumber - firstRows) * seatNumber * BACK_PRICE
}

fun buyTicket(aud: Array<Array<Char>>) {
    while (true) {
        val busy = Pair(
            enterNumber(OCCUPY_ROW_PROMPT) - 1,
            enterNumber(OCCUPY_SEAT_PROMPT) - 1
        )
        if (wrongInput(aud, busy)) {
            println(WRONG_INPUT)
        } else if (seatBusy(aud, busy)) {
            println(SEAT_PURCHASED)
        } else {
            markSeatBusy(aud, busy)
            val ticketPrice = getTicketPrice(busy.first, aud.size, aud[0].size)
            purchasedTickets += 1
            currentIncome += ticketPrice
            printTicketPrice(ticketPrice)
            break
        }
    }
}

fun wrongInput(aud: Array<Array<Char>>, busy: Pair<Int, Int>): Boolean {
    return busy.first < 0 || busy.second < 0 || busy.first >= aud.size
            || busy.second >= aud[0].size
}

fun seatBusy(aud: Array<Array<Char>>, seat: Pair<Int, Int>): Boolean {
    return aud[seat.first][seat.second] == BUSY
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

fun markSeatBusy(aud: Array<Array<Char>>, busy: Pair<Int, Int>) {
    aud[busy.first][busy.second] = BUSY
}

fun printTicketPrice(price: Int) {
    println("\nTicket price: $$price")
}
