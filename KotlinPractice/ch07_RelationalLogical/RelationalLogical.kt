package ch07_RelationalLogical

// 관계형 연산자 : < 작음, <= 작거나 같음, > 큼, >= 크거나 같음, == 같음, != 다름
// 논리 연산자 : && 논리곱, || 논리합, ! 논리부정

fun main() {
    // Operator Relational
    var x = 3
    var y = 5

    // 관계형 연산자의 값은 true 또는 false로 출력됨
    println(x == y) // F
    println(x != y) // T
    println(x > y) // F
    println(x >= y) // F
    println(x < y) // T
    println(x <= y) // T

    // And Operator(&&)
    println(true && true) // T : 둘 다 참일 때에만 참
    println(true && false) // F : 하나라도 거짓이면 거짓

    // Or Operator(||)
    println(false || true) // T : 하나라도 참이면 참
    println(false || false) // F : 둘 다 거짓일때만 거짓

    // Not Operator(!) : 반대로 값을 반환
    println(!true) // F
    println(!false) // T

    var i = 3
    var j = 5
    var r = false

    r = (i == 3) && (j != 3) // r = true && true => true
    println(r)

    r = (i != 3) || (j != 5) // r = false || false => false
    println(r)

    r = (i >= 5) // r = false
    println(!r) // true

}

