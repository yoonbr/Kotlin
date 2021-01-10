package ch09_IfElse

fun main() {
    // 순차문 : 순서대로 정의
    var kor = 100
    var eng = 90

    var tot = 0
    var avg = 0.0

    tot = kor + eng // 총점 구하기
    // avg = tot / 2  // 평균 구하기
    avg = tot / 2.0  // 평균 구하기 - 실수형 리터럴 이용

    println("총점 : $tot")
    println("평균 : $avg")
}