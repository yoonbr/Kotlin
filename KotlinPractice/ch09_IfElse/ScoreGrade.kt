package ch09_IfElse

fun main() {
    // 변수 생성
    var score = 0
    var grade = "F"

    // ReadLine 생성
    print("점수를 입력하세요 : ")
    score = (readLine() ?: "").toIntOrNull() ?: 0

    if(score >= 90){
        grade = "A"
    } else if(score >= 80){
        grade = "B"
    } else if(score >= 70){
        grade = "C"
    } else if(score >= 60){
        grade = "D"
    } else {
        grade = "F"
    }
    println("당신의 점수는 ${score}점 이며, 등급은 ${grade}입니다.")
}