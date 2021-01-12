package ch11_For

fun main() {
    var n = 100
    var sum = 0

    for(i in 1..n){
        sum = sum + i
    }

    println("1부터 ${n}까지의 합 : ${sum}")

    // for sum even
    var n1 = 5
    var sum1 = 0

    for (i in 1..n1) {
        if (i % 2 == 0) {
            sum1 = sum1 + i
        }
    }
    println("1부터 ${n1}까지 짝수의 합 : ${sum1}")

    // 직각 삼각형 만들기
    // 행 반복
    for (i in 1..5) {
        // 열 반복
        for (j in 1..i) {
            print("*")
        }
        println()
    }
}