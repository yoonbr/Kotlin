fun funAdd(x1:Int, x2:Int):Int{
    return x1 + x2
}

// return 되는 데이터의 자료형을 기재하지 않고 return 할 때 return 이라는
// 예약어도 사용하지 않음 - 마지막 표현식이 리턴되는 문장
val lambdaadd = {x1:Int, x2:Int -> x1+x2}

fun main() {
    println(funAdd(100, 200))
    println(lambdaadd(100, 200))
    println({x1:Int, x2:Int -> x1+x2}(100,200))
}