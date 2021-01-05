var v1 : Int = 30 // 자료형을 명시했고 변경이 가능한 변수
var v2 = 30 // 자료형을 생략하고 변경이 가능한 변수
val v3 = 70 // 자료형을 생략하고 변경이 불가능한 변수

// Top Level 변수는 초기화하지 않고 선언만 할 수는 없음
val g_n : Int = 20
fun main(args:Array<String>) {
    v1 = 80
    
    // null을 저장하고자 할 때는 자료형 뒤에 ?를 기재
    var v : Int? = null

    // 지역 변수는 초기화하지 않고 선언만 하는 것이 가능
    var _n : Int
    // v3 = 77 // error - val 변수
   println(v1)
    println(v3)


}