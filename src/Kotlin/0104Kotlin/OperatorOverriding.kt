data class DTO(val no:Int) {
    // 정수와 덧셈을 하는 연산자 오버로딩
    operator fun plus(arg : Int) : Int {
        return no + arg
    }

    // 동일한 다른 타입의 객체와 덧셈을 하는 연산자 오버로딩
    operator fun plus(arg : DTO) : Int {
        return no + arg.no
    }
}

fun main(args:Array<String>){
    val data1 = DTO(10)
    val data2 = DTO(20)

    // 연산자 오버라이딩을 하지 않은 경우는 내부 데이터끼리 연산을 수행해야 함
    var result = data1.no + data2.no
    println("result:${result}")

    // 인스턴스와 정수의 덧셈
    result = data1 + 50
    println("result:${result}")

    // 인스턴스와 인스턴스 덧셈
    result = data1 + data2
    println("result:${result}")
}