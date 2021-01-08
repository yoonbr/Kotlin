fun main(){
    val list = listOf<String>(
        "Lassie", "Hawaiifiveo", "Dexter", "Blair", "Bob", "Reland",
        "Secrid", "Fay", "Topcat", "Jamie", "Serena", "Fox", "Robin",
        "Hazad", "Sleeve", "Pouch")

    // 위의 데이터를 전부 출력
    list.forEach({temp -> print("${temp}\t")})
        println()

    // check 함수 사용
    // 매개변수가 1개이고 Boolean을 리턴하는 함수를 대입
    // filter 와 동일
    // 10글자 이상인 문자열이 1개라도 있는지 확인
//    println(list.any({temp -> temp.length >= 10}))
    // 매개변수가 한개이면 it 사용 가능
    println(list.any({it.length >= 10}))

    // 데이터의 개수
    println("${list.count()}")
    println("${list.max()}")
    // 글자수가 가장 많은 데이터 찾기
    // 1개의 매개변수와 리턴하는 함수를 대입
    println("${list.maxBy({it.length})}")
    // reduce는 매개변수가 2개이고 리턴이 있는 함수를 대입
    // println("${list.reduce({tot, next -> tot + next.length})}")
    println("${list.fold(0,{tot, next -> tot + next.length})}")

}