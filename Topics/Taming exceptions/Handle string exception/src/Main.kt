fun main() {
    val index = readln().toInt()
    val word = readln()
    println(if (index in 0 until word.length) word[index]
    else "There isn't such an element in the given string, please fix the index!")
}