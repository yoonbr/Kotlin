package ch13_BreakContinue

fun main() {
    var kor = IntArray(3)

    kor[0] = 90
    kor[1] = 95
    kor[2] = 89

    var tot = 0
    for (k in kor) {
        tot += k
    }
    var avg : Double = tot / kor.size.toDouble()

    println("총점 : $tot, 평균 : ${String.format("%.2f", avg)}")
}