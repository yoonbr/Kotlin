package ch09_IfElse

fun main() {
    // 수를 두 개 입력받아 비교 후 큰 수를 출력
    print("첫 번째 수를 입력하세요 : ")
                        // 값이 공백이면 empty, int의 값이 null이면 0
    var first : Int = ((readLine()) ?: "").toIntOrNull() ?: 0

    print("두 번째 수를 입력하세요 : ")
    var second : Int = ((readLine()) ?: "").toIntOrNull() ?: 0

    if (first >= second) {
        println("큰 값 : $first")
    } else {
        println("큰 값 : $second")
    }
}