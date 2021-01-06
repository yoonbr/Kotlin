class Outer{
    var name : String = "noname"
    // 클래스 안에 존재하는 클래스라서 Nested Class
    inner class Inner{
        fun method(){
            println("내부 클래스의 메소")
            println("${name}")
        }
    }
}

class AnonymousOuter{
    // 내부 익명 클래스
    // 외부에서 접근하기 위해서 private 추가
    // 외부에서 접근할 것이 아니라면 private 은 필요 없음

    private val anonymousClass = object {
        fun innerMethod(){}
    }

    fun outerMethod(){
        anonymousClass.innerMethod()
    }
}

class CompanionOuter{
    companion object static{
        fun method(){
            println("static 메소드처럼 사")
        }
    }
}

fun main(){
    // inner가 없을 때
    // 내부 클래스의 인스턴스 만들기
    // inner Class인 경우는 외부 클래스의 인스턴스를 통해서 생성해야 함
    // val innerObj = Outer.Inner()

    // inner가 있을때
    val innerObj = Outer().Inner()
    innerObj.method()

    CompanionOuter.static.method()
}