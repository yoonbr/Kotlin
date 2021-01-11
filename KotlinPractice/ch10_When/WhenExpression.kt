package ch10_When

// When Expression == Switch Expression
// switch 대신 when

fun main() {
    var x = 3

    // [->] : arrow, gose to
    when (x) {
        1 -> println("x=1") // x가 1이면 "x=1"을 출력
        2 -> println("x=2") // x가 2이면 "x=2"을 출력
        else -> {
            println("X") // 둘 다 아니면 "X"를 출력
        }
    }

    // When Statement
    println("가장 좋아하는 프로그래밍 언어는?")
    print("1. C\t")
    print("2. C++\t")
    print("3. C#\t")
    print("4. Java\t")
    print("5. Kotlin\n")

    val choice = (readLine() ?: "").toIntOrNull() ?: 0

    when (choice) {
        1, 2 -> println("C/C++ 선택")
        in 3..4 -> println("C# 또는 Java 선택") // 범위 설정시 in 0..0 !in : 범위가 아니라면
        else -> println("LOVE Kotlin!")
    }
}