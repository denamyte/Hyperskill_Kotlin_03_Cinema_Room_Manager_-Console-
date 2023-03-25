import java.util.Scanner

fun main() {
    val scanner = Scanner(System.`in`)
    val list = List(scanner.nextInt()) { scanner.nextInt() }
    val nmList = List(2) { scanner.nextInt() }
    val nextTo = list.zipWithNext().any {
        it.first in nmList && it.second in nmList
    }
    println(if (nextTo) "NO" else "YES")
}

