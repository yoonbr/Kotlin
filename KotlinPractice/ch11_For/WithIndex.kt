package ch11_For

fun main() {
    // 배열의 위치로부터 withindex라는 func을 출력 : 문자열을 하나씩 읽어줌
    // Index는 0부터 시작
    for((index, value) in "Hello".withIndex()){
        println("${index + 1} - $value")
    }
}