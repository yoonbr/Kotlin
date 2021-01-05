fun main(args:Array<String>){
    val score : Int = 90
    // score가 90이상이면 합격 그렇지 않으면 불합격이라고 출력
    if(score >= 90) println("합격") else println("불합격")
    println("합격"); println("합격") //문장이 중복되면 세미콜론을 붙임

    var food : String = "Pasta"
    when(food) {
        "Pasta" -> println("noddle")
        "Cookie" -> println("bakery")
        "coke" -> println("drink")
        else -> println("ETC")
    }

    food = "ramen"
    // 값을 여러 개 나열하는 것이 가능
    when(food) {
        "Pasta", "ramen" -> println("noddle")
        "Cookie" -> println("bakery")
        "coke" -> println("drink")
        else -> println("ETC")
    }

    // 값에 Boolean 식을 사용하는 것도 가능
    when{
        food is String -> println("food는 문자열")
        else -> println("food는 문자열이 아님")
    }

    val sc : Int = 50
    when {
        sc in 60..100 -> println("합격")
        else -> println("불합격")
    }
}