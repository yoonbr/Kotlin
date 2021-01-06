// 상속을 받으려면 open이 필요함
// var일때 아래 클래스가 val일 수는 없음
open class PropertySuper{
    open val msg : String = "adam"
}

class PropertySub:PropertySuper(){
    // 프로퍼티 Overriding
    // val -> var, val
    // var -> var
    override val msg : String = "BONNIE"
}