//open class Super{
//    open fun method(){
//        println("상위 클래스의 메소드")
//    }
//}

class Super{
    fun method(){
        println("상위 클래스의 메소드")
    }
}
/*
// 상속을 통해서 기능을 확장
// 상위 클래스가 open class가 아니면 상속이 되지 않음
class Sub:Super(){
    fun disp(){
        println("클래스 기능 확장")
    }
}
*/

// 클래스의 기능 확장 (상위 클래스에 open을 사용하지 않을 때)
fun Super.disp(){
    println("클래스 기능 확장")
}

fun main(){
//    val sub : Sub = Sub()
//    sub.disp()

    val superExt = Super()
    superExt.disp()
}

