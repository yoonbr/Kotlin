abstract class AbstractClass{
    abstract fun method()
}

interface Inter1{
    fun method()
}

interface Inter2{
    fun method(){
        println("인터페이스2에 구현된 메소드")
    }
}

interface Inter3{
    fun method(){
        println("인터페이스3에 구현된 메소드")
    }
}

class Sample: AbstractClass(), Inter1, Inter2, Inter3{
    override fun method() {
        // 구현된 메소드가 1개일 때는 super.으로 호출
        // super.method()

        // 구현된 메소드가 2개 이상일 때는
        // super<클래스이름 또는 인서페이스 이름>.으로 호출
        super<Inter2>.method()
        super<Inter3>.method()
        println("한번만 오버라이딩 하면 됩니다.")
    }
}

fun main(){
    val sample : Sample = Sample()
    sample.method()
}