// 생성자를 만들지 않는 경우에는 매개변수가 없는 생성자가 자동으로 만들어 짐
class User1 {}

// 매개변수가 없는 생성자를 직접 생성
class User2 constructor(){
    // 인스턴스가 생성될 때 수행하는 블럭
    init {
        println("매개변수가 없는 주 생성자 작성")
    }
}

// 주 생성자는 constructor 생략 가능
// num앞에 val이나 var을 붙이면 프로퍼티로 생성
class User3 (var num:Int){
    // 인스턴스가 생성될 때 수행하는 블럭
    init {
        println("매개변수가 있는 주 생성자 작성")
        println("매개변수가 없는 생성자는 만들어지지 않음")
    }
}
fun main() {
    var user1 = User1() // 생성자가 없는 클래스의 인스턴스 생성
    var user2 = User2()
    var user3 = User3(10) // 매개변수가 없는 인스턴스는 존재하지 않음
    user3.num = 100
}