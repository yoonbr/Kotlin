class MyClass<T>{
    var info : T? = null
}

open class Super{}

class Sub:Super(){}

// 제너릭의 자료형이 Super
class MyClass1<T:Super>{
    var info : T? = null
}
// T로 만들어진 프로퍼티에 Super 또는 Super의 하위 클래스 타입의 인스턴스를 대입할 수 있음상
// - 공변성(covariance)
class MyClass2<out T:Super>{
}

// T로 만들어진 프로퍼티에 Sub 또는 Sub의 위 클래스 타입의 인스턴스를 대입할 수 있음
// - 반공변성(contravariance)
class MyClass3<out T:Super>{
}


fun main(){
    // MyClass의 인스턴스를 생성
    var obj1 = MyClass<String>()
    var obj2:MyClass<String> = MyClass()
    // var obj3 = MyClass() // 제너릭 자료형을 설정하지 않아서 에러

    var obj20:Super = Sub()
    // 상위 클래스의 참조형 변수에
    // 하위 클래스 타입의 인스턴스를 대입하는 것이 가능

    // 제너릭에서는 상위 클래스 타입에 하위 클래스 타입을 대입할 수 없음
    // var obj30:MyClass<Super> = MyClass1<Sub>()

}