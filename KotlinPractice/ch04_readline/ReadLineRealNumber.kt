package ch04_readline

fun main() {
    print("키를 입력하세요 : ")
    // 앞에 내용이 null이면 empty로 초기화
    val input = readLine() ?: ""
    // null 값이면 0으로 초기화
    // toDouble로 강제 형변환
    val tall : Double = input?.toDouble() ?: 0.0
    // val tall : Double = input.toDoubleOrNull() ?: 0.0
    println("키는 ${tall}입니다.")
}