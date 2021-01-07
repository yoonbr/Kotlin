// Top Level의 변수 : 모든 곳에서 사용 가능
// var n : Int = 0

// 매개변수가 없고 리턴타입이 없는 함수나 람다표현식을 리턴
fun outer():() -> Unit{
    // n은 지역 변수 - 함수 외부에서는 접근할 수 없음
    var n : Int = 0

   return {
        println("n:${n}")
        n = n + 1
    }
}

fun main() {
    // outer 의 리턴되는 람다 표현식이 closure에 대입
    val closure = outer()
    // 호출할 때 마다 1씩 증가시켜서 출력
    closure()
    closure()
}