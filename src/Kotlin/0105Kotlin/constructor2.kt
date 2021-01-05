class Person(var num:Int) {
    init{
        println("초기화 블럭")
    }
    // 보조 생성자
    // var이나 val을 매개변수에 붙일 수 없음
    // Priamry Constructor가 있는 경우에는
    // Secondary Constructor에서 반드시 호출해야 함
    constructor(num:Int = 0, name:String=""):this(num){
        println("보조 생성자")
        this.num = num
    }
}

fun main() {
    var person = Person()
}