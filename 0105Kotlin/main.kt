// 1부터 매개변수까지의 합계를 구해주는 함수
fun sum(a:Int):Int{
    var result = 0
    for(i in 1..a) {
        result += i
    }
    return result
}
// 함수의 내용이 한 줄이고 리턴하는 문장만 존재
fun add1(a:Int, b:Int):Int{
    return a + b
}
// 위와 동일한 함수
fun add2(a:Int, b:Int):Int = a+b

// return 타입 생략
fun add3(a:Int, b:Int) = a+b

fun main(args: Array<String>) {
    // 함수를 호출할 때는 함수의 소속, 함수 이름, 매개 변수, 리턴 타입 확인
    var result = sum(10)
    println(result)
    // TopLevel의 변수나 함수는 나중에 선언해도 됨
    outer()

    println(add1(100,200))
    println(add2(100,200))
    println(add3(100,200))

    println(fibo(6))
}

// 함수 안에 함수를 선언하는 것도 가능
// 리턴하는 데이터가 없으면 Unit이라고 작성해도 되고
// 리턴하는 자료형을 생략하는 것도 가능
fun outer(){
    // 함수 안에 만든 내부 함수
    fun inner(){
        println("inner 함수")
    }
    // 내부 함수를 호출
    inner()
}

// n번째 피보나치 수열의 값을 구해주는 함수
fun fibo(n:Int):Int {
    var n1 = 1
    var n2 = 1
    var result = 1
    for(i in 3..n){
        result = n1 + n2
        n1 = n2
        n2 = result
    }
    return result
}