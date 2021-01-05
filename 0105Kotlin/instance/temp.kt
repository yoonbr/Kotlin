package instance
class MyClass {
}

// 생성자를 만들지 않았지만 매개변수가 없는 생성자가 존재
open class Base1{}

// Base1을 상속받는 Drive1 클래스를 생성
class Drive1 : Base1(){
}

// 매개변수가 String인 주 생성자를 작성한 클래스
// 매개변수가 없는 생성자가 존재하지 않음
open class Base2(var name:String){}

// Base2를 상속받는 Drive2 클래스를 생성
// 상위 클래스의 주 생성자를 호출
class Drive2 : Base2("문자열을 추가해주어야 함"){
}

// 하위 클래스에 주 생성자가 존재하는 경우
class Drive3(name:String, num:Int):Base2(name){

}

// 하위 클래스에 보조 생성자가 있는 경우
class Drive4:Base2{
    // 보조 생성자에서 상위 클래스의 생성자를 호출할 떄는 super로 호출
    constructor(name:String):super(name){}
}

open class Super{
    open fun method(){
        println("상위 클래스의 메소드")
    }
}

class Sub : Super() {
    override fun method() {
        super.method()
        println("하위 클래스의 메소드")

    }
}

fun main() {
    // MyClass의 인스턴스 생성
    val myClass1 = MyClass()
    val myClass2 = MyClass()
    // 클래스 안에 아무것도 정의하지 않았는데 equals 메소드 호출 가능
    // Any 클래스로 부터 상속을 받았기 때문에 Any Class에 만들어진 것을 사용하는 것이 가
    println(myClass1.equals(myClass2))
}