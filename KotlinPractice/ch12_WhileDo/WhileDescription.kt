package ch12_WhileDo

// while, do~while : 현업에서는 데이터가 있는 만큼 반복하는 목적으로 사용

fun main() {
    var cnt = 0                 // 초기식
    while (cnt < 3) {            // 조건식
        println("Hello")        // 실행문
        cnt++                   // 증감
    }

    // while문 사용하여 짝수의 합
    var sum = 0

    var i = 1                   // 초기식
    while (i <= 100) {          // 조건식
        if (i % 2 == 0) {       // 필터링(조건처리)
            sum += i            // 실행문
        }
        i++                     // 증감식
    }
    println("1부터 100까지 짝수의 합은 $sum")

    // whileFibonacci
    // 1부터 20까지 범위 내에 있는 피보나치 수열을 출력
    var first = 0
    var second = 1

    while (second <= 20) {
        print("$second \t")
        val temp = first + second
        first = second
        second = temp
    }

    // Do While
    // 실행문이 최소 1번은 실행
    // 실행 후 조건식을 판단
    var count = 0           // 초기식
    do {
        println("Hello")    // 실행문
        count++             // 증감식
    } while (count < 3)     // 조건식

    // Do While Demo
    // 1부터 100까지의 정수 중 3배의 배수이면서 4의 배수인 정수의 합
    var ex = 0
    var j = 1                               // 1. j가 1부터
    do {
        if(j % 3 == 0 && j % 4 ==0) {       // 3. 3의 배수이거나 4의 배수일때
            ex += j                         // 4. ex에 j의 값을 누적
        }
        j++                                 // 5. j를 1씩 증가
    } while (j <= 100)                      // 2. i가 100보다 작거나 같을 동안
    println(ex)
}