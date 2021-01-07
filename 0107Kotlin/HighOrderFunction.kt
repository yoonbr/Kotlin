// 함수를 매개변수로 받거나 리턴하는 함수를 고차함수라고 함

// 정수 1개를 매개변수로 받고 정수를 리턴하는 함수로 매개변수를 대입받는 고차함
fun high1(func:(Int) -> Int){
    println(func(100))
}

// 정수 1개와 정수 1개를 매개변수로 받아서 정수를 리턴하는 함수를
// 매개변수로 받는 함수
fun high2(n:Int, func:(Int) -> Int){
    println(func(n))
}

// {x:Int -> x+200} 와 동일한 Top Level Function
fun topFunc(x:Int):Int{
    return x + 200
}

// Class 안에 생성
class funcClass{
    fun topFunc(x:Int):Int{
        return x + 200
    }
}
fun main(){
    high1({x:Int -> x+200})
    // 정수 1개와 정수를 받아서 정수로 리턴하는 람다식을 매개변수로 대입
    high2(200, {x:Int -> x+200})
    // 매개변수가 1개인 람다는 it 사용 가능 : 매개변수 생
    high2(200, {it + 200})
    // trailing closure : 람다 표현식이 마지막에 대입되는 경우는
    // () 외부에 작성 가능
    high2(200){it + 200}

    // TopLevel에서 만든 TopFunc 함수를 대입
    high2(200, ::topFunc)

    // FuncClass에서 만든 TopFunc 함수를 대입
    // high2(200, funcClass::topFunc)

    // 배열 생성
    var ar = arrayOf(100, 20, 32, 29)
    // 하나의 매개변수를 받아서 Boolean을 리턴하는 람다 표현식을 대입하면
    // 결과가 true인 데이터만 모아서 리턴하는 함수 - filter
    // println(ar.filter({T -> Boolean}))
    // 짝수만 골라내기
    println(ar.filter({x -> x % 2 == 0}))
}