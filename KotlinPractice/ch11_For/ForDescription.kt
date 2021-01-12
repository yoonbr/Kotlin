package ch11_For

fun main() {
    // Hello 3번 출력
    for (i in 0..2) {
        println("Hello")
    }
    // Kotlin 3번 출력
    for (i in 1..3) {
        println("Kotlin")
    }

    // 1~5까지 하나씩 출력
    for (i in 1..5) {
        print("$i\t")
    }
    println()

    // 1~5까지 2씩 증가
    for(i in 1..5 step 2) {
        print("$i\t")
    }
    println()

    // 5~1까지 감소하며 출력
    for(i in 5 downTo 1) {
        print("$i\t")
    }
    println()

    // 5~1까지 2간격으로 감소하며 출력
    for(i in 5 downTo 1 step 2) {
        print("$i\t")
    }
    println()

    // 1~4까지 (5를 제외)
    for (i in 1 until 5) {
        print("$i\t")
    }
    println()

    // 문자열 하나하나 출력
    for(c in "HelloWorld"){
        print("$c\t")
    }
    println()
}