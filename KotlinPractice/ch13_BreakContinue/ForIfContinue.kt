package ch13_BreakContinue

// continue문을 사용하여 반복문(for, while, do)을 다음 반복으로 이동

fun main() {
    for (i in 1..5) {
        if (i % 2 == 0) {
            continue // 출력하지 않고 다시 반복문으로 돌아감
        }
    println("$i")
    }

    // Label
    // break는 가까운 for문을 빠져나오는데, label(loop@)을 사용하면
    // label이 있는 곳으로 해당 반복문을 종료시킴
    var count = 0
    loop@ for (i in 1..100) {
        for (j in 1..100) { // 만번 출력
            println("Hello")
            if(count++ >= 5) {
                break@loop
            }
        }
    }
    println()
}