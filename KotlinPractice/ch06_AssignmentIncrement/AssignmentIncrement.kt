package ch06_AssignmentIncrement

// 할당 연산자와 증감 연산자 : =, +=, -=, *=, /=, %=, ++, --
// 할당(대입) 연산자 : 변수에 특정 값으로 초기화하면 자동 유추에 의해서 형식이 결정됨

fun main() {
    var name = "Kotlin"
    var version = 1.4

    println("$name $version")

    // Swap Demo
    var i = 100
    var j = 200

    println("처음값 : $i, $j")

    // 변수값 서로 바꾸기
    // 임시변수 한개 생성
    var temp : Int = i
    i = j
    j = temp

    println("변경값 : $i, $j")

    // Increment Number (증가)
    var num = 10
    // num = num + 1
    num += 1            // ShortcutOperator
    println(num)

    // Decrement Number (감소)
    var num1 = 10
    // num = num - 1
    num1 -= 1
    println(num1)

    // Increment Operator
    var num2 = 100
    ++num2  // 증가 연산자
    println(num2) // 101

    // Decrement Operator
    var num3 = -100
    --num3 // 감소
    println(num3) // -101

    // Prefix Operator
    // 전위(Prefix) 증감 연산자 : 우선순위가 높음
    // 할당 연산자보다 우선순위가 높음
    var a = 3
    var b : Int = ++a // 4로 증가된 데이터를 b에 넣기
    println(b) // 4

    // Postfix Operator
    // 후위(Postfix) 증감 연산자 : 우선순위가 대입 연산자보다 낮음
    var c = 3
    var d : Int = c++ // d라는 변수에 c값을 먼저 할당
    println(d) // 그 다음에 c값이 증가 - 3 출력
}