package ch08_Bitwise

fun main() {

    var x : Int = 0b1010 // 2진수 10
    var y : Int = 0B1100 // 2진수 12

    println(x)
    println(y)

    // Bit And
    var z : Int = x and y // 1 and 1 -> 1
    println(z) // 이진수 1000 = 8

    // Bit Or
    var z1 : Int = x or y // 1 or 0 -> 1
    println(z1) // 이진수 1110 = 14

    // Bit Xor
    var z2 : Int = x xor y // 1 xor 0 -> 1, 서로 다르면 1
    println(z2) // 이진수 0110 = 6

    // Bit Not
    var z3 : Int = x.inv() // 0000~_1010 -> 1111~_0101
    println(z3) // 음수의 이진수 1111~_0101 = -11

    // Shift Operator Demo
    var num = 2 // Ob_0000_0010

    println(num shl 1) // 1을 왼쪽으로 한 칸 이동 Ob_0000_0100
    println(num shr 1) // 1을 오른쪽으로 한 칸 이동 Ob_0000_0001

}