class MyClass1 {
    // 프로퍼티 생성
    var myVal = 0
    // 메소드 생성
    fun myFunc(){
        println("myVal:${myVal}")
    }
}

fun main(){
    // 인스턴스 생성 (new가 없음)
    val myClass = MyClass1()
    // 인스턴스를 이용해서 멤버 호출
    myClass.myVal = 100
    myClass.myFunc()
}