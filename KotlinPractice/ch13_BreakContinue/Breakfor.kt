package ch13_BreakContinue

fun main() {
    // 아무것도 하지 않는 프로그램
    for (i in 0 until 5) {
        if (i >= 0){
            break               // 반복문(for)을 빠져나옴
            println("라인 실행?") // 실행 안됨
        }
    }

    // BreakInfiniteLoop
    var number = 0

    // 무한 루프
    while (true){
        println("${++number}")

        if(number >= 5) {
            break               // break 문으로 무한 루프 빠져나옴
        }
    }
}