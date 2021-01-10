package ch05_operator

// 연산자(operator): 데이터와 변수를 가지고 더하기, 빼기와 같은 연산 작업을 수행

fun main() {
    // Operator Description
    // 이항 연산자
    println(3 + 5)
    println(3 - 5)
    println(3 * 5)
    println(3 / 5) // 몫은 0, 나머지 3
    println(3 % 5) // 나머지 : 3

    // 단항 연산자(Unary Operator) : 항이 1개 (+,-)
    var value : Int = 0

    value = 8
    value = +value
    println(value)

    value = -8
    value = +value // + 를 붙인다고 해서 양수가 되는 것이 아님
    println(value)

    value = -8
    value = -value // 음수에 - 를 붙이면 양수로 변환
    println(value)

    value = 8
    value = -value
    println(value) // -8로 변환

    // Casting Operator
    var num : Int = 3.14.toInt()
    println(num) // 3

    // Number To String
    var days = 29
    println("2월달은 " + days + "일입니다.")              // 암시적(Implicit) - 형변환 생략
    println("2월달은 " + days.toString() + "일입니다.")   // 명시적(Explicit) - 형변환 사용 > 권장
    println("2월달은 ${days}일입니다.")                   // 템플릿 문자열, 문자열 보간법

}