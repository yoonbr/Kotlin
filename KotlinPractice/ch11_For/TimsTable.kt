package ch11_For

// 구구단 가로로 출력하기

fun main() {
    for(i in 2..9){
        // 문자열로 4칸 잡고 i를 출력
        print("${String.format("%5s", i)}단")
    }
    println()

    for(i in 1..9) { // 행(세로) 출력
        for(j in 2..9) { // 열(가로) 출력
            print("$j*$i=${String.format("%2s", (j * i))} ")
        }
        println()
    }
}