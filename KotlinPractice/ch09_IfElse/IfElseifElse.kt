package ch09_IfElse

fun main() {
    var num1 = 10
    var num2 = 20

    if(num1 > num2){
        // 조건처리 1
        println("num1이 큽니다.")
    } else if (num1 < num2){
        println("num2이 큽니다.")
    } else{
        println("둘 다 같습니다.")
    }
}