package ch03_CharStringBoolean

// 숫자 이외의 데이터 형식 : Boolean, Char, String 등의 키워드로 숫자 이외의 데이터 저장

fun main(){
    // Char
    var grade : Char = 'A'
    var kor = 'D' // 문자 하나를 자동적으로 유추하여 Char로 선언됨

    println(grade)
    println(kor)

    // String
    val name : String = "BONNIE"

    println("Hello, I'm ${name}.")

    // String interpolation : 문자열 보간
    // 템플릿 문자열
    var msg : String = "String interpolation"

    println("Message: " + msg)
    println("Message: ${msg}")
    println("Message: $msg") // 단순 문자열 1개 출력시에는 중괄호 생략 가능

    // Boolean
    // 논리 자료형 : 참 또는 거짓 값을 저
    val bln : Boolean = true // 참
    println(bln)

    val isFalse = false // 거짓
    println(isFalse)

}
