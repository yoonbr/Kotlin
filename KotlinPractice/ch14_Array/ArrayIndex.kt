package ch14_Array

fun main() {
    var arr = IntArray(3) // 3칸 짜리의 배열
    arr[0] = 11
    arr[1] = 22
    arr[2] = 33
    // arr[3] = 4 // error - 범위를 벗어남

    var index = 0
    println(arr[index++]) // 0번째 출력하고 1증가 = 11
    println(arr[index++]) // 1번째 출력하고 1증가 = 22
    println(arr[index++]) // 2번째 출력하고 1증가 = 33

    println(arr[--index])
    println(arr[--index])
    println(arr[--index])
}