package ch02_number

// 숫자 데이터 형식 : Int, Long, Float, Double, Decimal, Byte, Short 등의 키워드로 숫자 데이터 저장

fun main(){
    var min : Int = -2147483648; // 정수형이 가질 수 있는 가장 작은 값
    var max : Int = 2147483647 // 정수형이 가질 수 있는 가장 큰 값

    println("Int 변수의 최소값 : ${min}")
    println("Int 변수의 최대값 : ${max}")

    var PI : Double = 3.141592 // 배정밀도 부동 소수점 변수를 선언하고 값을 할당
    println("PI : ${PI}")

    var i : Int = 1_000 // 정수
    var b : Byte = 127 // 작은 정수
    var s : Short = 1234 // 정수
    var l : Long = 1234L // 큰 정수 (L접미사)
    var f : Float = 3.14F // 32비트 실수 (F접미사)
    var d : Double = 3.14 // 64비트 실수

    // 숫자 구분자 (,대신 _)
    var lValue = 2_147_483_647

    // 형식 변환
    val ii : Int = 1234
    var ll : Long = ii.toLong()

    val dbl = 12.34
    // val iii : Int = dbl // Type mismatch error
    val iii : Int = dbl.toInt() // 12 출력
    println(iii)

}