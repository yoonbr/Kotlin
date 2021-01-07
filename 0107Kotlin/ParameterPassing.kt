// 람다 함수
val lambda:() -> Boolean = {
    println("파라미터 전송")
    true
}

// call by value : 파라미터가 데이터
fun callByValue(b:Boolean):Boolean{
    println("call by value")
    return(b)
}
// call by name : 람다 표현식이나 변수를 대입
fun callByName(b:()->Boolean):Boolean{
    println("call by name")
    return b()
}

fun main(){
    // 데이터를 주기
    callByValue(lambda())
    // 이름을 주기
    callByName(lambda)
}