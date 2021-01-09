package ch04_readline

fun main() {
    println("Insert Name : ")
    var name = readLine() // ReadLine을 이용해서 내용을 입력받고
    println("Hello $name") // 해당 내용을 양식에 맞게 출력함
}