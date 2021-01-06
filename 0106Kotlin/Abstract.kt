// 추상 클래스는 여러 클래스들에 동일한 내용이 있는 경우에
// 다형성 구현을 위해서 많이 생성
// Java에서 대표적인 예가 Calendar 클래스

// 추상 클래스 - 인스턴스 생성 못함
/*
abstract class SmartPhone{
    // 추상 메소드는 내용을 가지지 못함 (중괄호 없어짐)
    abstract fun call()
}
 */

interface SmartPhone{
    // 인터페이스는 abstract를 붙일 필요가 없음
    fun call()
}

// 인터페이스를 구현할 때는 생성자를 호출할 필요가 없음
class Android:SmartPhone{
    override fun call() {
        println("안드로이드에서 전화걸기")
    }
}
class IPhone:SmartPhone{
    override fun call() {
        println("아이폰에서 전화걸기")
    }
}

fun main(){
    var smartphone:SmartPhone = Android()
    smartphone.call()

    smartphone = IPhone()
    smartphone.call()

    // 추상 클래스는 인스턴스를 생성할 수 없음
    // smartphone = SmartPhone()
    // smartphone.call()
}