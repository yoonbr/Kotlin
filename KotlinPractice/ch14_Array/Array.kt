package ch14_Array

// 배열(Array) : 하나의 이름으로 같은 데이터 형식을 여러 개 보관해 놓는 그릇

fun main() {
   // 문자열 == 문자의 배열
    var arr = "Kotlin"

    println(arr[0]) // K
    println(arr[1]) // o
    println(arr.get(2)) // t (get은 잘 안씀)

    // ArrayDescription
    var numbers = arrayOf(3840, 2160)

    println("${numbers[0]} * ${numbers[1]}")

    var phones = arrayOf("112", "119") // array는 2개의 값을 가짐
    for (phone in phones) { // phones의 값 만큼 반복
        println(phone)
    }

    // Array Languages
    // languages[1] 값을 "영어"로 수정
    var languages = arrayOf("Korean", "English", "Spanish")
    // languages.set(1, "영어")
    languages[1] = "영어" // 이렇게 변경 가능

    for(language in languages) {
        println(language)
    }

    // 정수 배열 타입의 intArrayOf
    var nums : IntArray = intArrayOf(1, 2, 3)
    for (n in nums) {
        println(n)
    }

    // 실수 배열 타입의 floatArrayOf
    var floats = floatArrayOf(1.1f, 1.2f, 1.4f)
    for (f in floats) {
        println(f)
    }

    // arrayListof
    // <> - generic
    var favo = arrayListOf<String>("C#", "Java", "Kotlin")
    for(fav in favo) {
        println(fav)
    }
}