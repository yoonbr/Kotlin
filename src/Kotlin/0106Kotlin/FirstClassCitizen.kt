
// 이 함수는 pure function
// 매개변수의 값이 동일하면 동일한 결과가 리턴, 기존 매개변수의 값을 변경하지 않았음
// 결과는 새로 생성해서 리턴

fun func1(first:Int, second:Int):Int{
    var result = first + second
    return result
}

// pure function이 아님
// 동일한 입력에 다른 결과가 나올 수 있음
fun func2(n:Int):Int{
    var result = (Math.random() * n).toInt()
    return result
}

// 함수는 일급 객체
// 변수에 저장이 가능하고 내부에 다른 함수나 클래스를 포함할 수 있음
fun someFunc(){
    println("kotlin에서 함수는 일급 객체")
}

fun main(){
    println(func1(10, 20))
    println(func1(10, 20))
    println(func2(10))
    println(func2(10))

    // 변수에 함수를 대입
    val f = ::someFunc
    // 변수를 이용해서 함수를 호출
    f()

}