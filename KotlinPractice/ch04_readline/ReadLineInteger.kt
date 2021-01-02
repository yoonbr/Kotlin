package ch04_readline

fun main() {
    print("나이를 입력하세요 : ")
    // 앞에 내용이 null이면 empty로 초기화
    val temp = readLine() ?: ""
    // null 값이면 0으로 초기화
    // toInt로 강제 형변환
    val intAge : Int = temp?.toInt() ?: 0
    println("나이는 ${intAge}입니다.")
}