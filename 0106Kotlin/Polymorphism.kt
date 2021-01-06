open class PolymorphismSuper{
    open fun method(){
        println("상위 클래스의 메소드")
    }

}
class PolymorphismSub : PolymorphismSuper(){
    override fun method() {
        println("하위 클래스의 메소드")
    }

}

fun main() {
    var polymorphismSuper : PolymorphismSuper
    = PolymorphismSuper()
    // 상위 클래스의 메소드를 호출
    polymorphismSuper.method()

    polymorphismSuper = PolymorphismSub()
    // 하위 클래스의 메소드를 호출
    polymorphismSuper.method()


}