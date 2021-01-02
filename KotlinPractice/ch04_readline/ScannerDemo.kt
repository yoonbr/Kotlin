package ch04_readline

import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)
    println("정수: ")
    val num = scanner.nextInt()

    println("실수: ")
    val real = scanner.nextDouble()

    println("${num}-${real}")
}