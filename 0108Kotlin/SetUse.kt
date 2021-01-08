fun main(){
    var touches = setOf<String>(
        "Blair", "Lassie", "Hawaiifiveo", "Dexter",
        "Jamie", "Bob", "Reland"
    )
    // 전체 순회
    for(freitag in touches){
        print("${freitag}\t")
    }
    println()

    // 첫번째 하나 꺼내서 출력
    println("${touches.first()}")

    // 마지막 하나 꺼내서 출력
    println("${touches.last()}")
}