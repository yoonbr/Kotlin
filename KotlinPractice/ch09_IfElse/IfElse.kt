package ch09_IfElse

fun main() {
    // 조건문
    // score가 60 이상이면 '합격' 출력
    var score = 59
    if (score >= 60) {
        println("합격")
    } else {
        println("불합격")
    }

    var x = 10
    if (x == 10) {
        println("x는 ${x}입니다.")
    }

    if (x != 20) {
        println("x는 20이 아닙니다.")
    }

    // 문자열 비교(==, equals)
    var s1 = "Hello."
    var s2 = "Hello."

    if (s1 == s2) {
        println("Same")
    }

    if (s1.equals(s2)) {
        println("Same")
    }

    // if not
    var bln : Boolean = false

    if(!bln) {
        println("bln : false -> ! -> true")
    }

    // if nested (if 중첩)
    var name = "Kotlin"
    var version = 1.4

    if(name == "Kotlin") {
        if(version == 1.4) {
            println("$name $version")
        }
    }

    // if and
    var number = 1_234

    // 2개의 조건이 모두 만족하면
    if(number == 1234 && number >= 1000){
        println("동일")
    }

    // 2개의 조건중 하나라도 만족하면
    if(number == 1234 || number <= 1000){
        println("동일")
    }

    // Else
    if(1 != 1) {
        println("조건식이 참이기에 현재문장이 실행")
    } else {
        println("조건식이 거짓이기에 현재문장이 실행")
    }

}
