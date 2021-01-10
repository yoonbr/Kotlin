package ch09_IfElse

fun main() {
    var first = 3
    var second = 5

    val max = if (first > second) first else second

    println("MAX: $max")

    val maxValue = if(first > second){
        println("first")
        first // 값을 반환해서 maxValue에 대입
    } else {
        println("second")
        second // 값을 반환해서 maxValue에 대입
    }
    println("MaxValue : $maxValue")
}