package cinema


const val ROWS_PROMPT = "Enter the number of rows:"
const val SEATS_PROMPT = "Enter the number of seats in each row:"
const val FRONT_PRICE = 10
const val BACK_PRICE = 8
const val SMALL_AUDITORIUM_LIMIT = 60

fun main() {
    val rowNumber = enterNumber(ROWS_PROMPT)
    val seatNumber = enterNumber(SEATS_PROMPT)
    val income = calcTotalIncome(rowNumber, seatNumber)
    printIncome(income)
}

fun enterNumber(prompt: String): Int {
    println(prompt)
    return readln().toInt()
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

fun printIncome(income: Int) {
    println("Total income:\n\$$income")
}
