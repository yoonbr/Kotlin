data class Item(val num:Int, val name:String, val price:Int, val size:String) {}

fun main(){
    val list = listOf<Item>(
        Item(1, "Jamie", 186000, "small"),
        Item(2, "Topcat", 448000, "xxlarge"),
        Item(3, "Hawaiifiveo", 188000, "medium"),
        Item(4, "Dexter", 345000, "xlarge"),
        Item(5, "Lassie", 288000, "large")
    )

    // 200000이 넘는 데이터만 추출
    println("200000 초과:${list.filter({it.price > 200000})}")

    // 200000 에서 400000 사이 데이터 추출
//    println("200000 ~ 400000:" +
//            "${list.filter({it.price >= 200000 && it.price <= 400000})}")
    // 이렇게 바꿔쓸 수 있음
    println("200000 ~ 400000:" +
            "${list.filter({it.price in 200000..400000})}")

    // Lassie와 jamie에 해당하는 데이터만 조회
    val keywords = mutableListOf<String>()
    keywords.add("Lassie")
    keywords.add("Jamie")
    println("Lassie and Jamie : ${list.filter({it.name in keywords})}")
    // Set도 사용 가능 : 중복으로 내용을 입력해도 하나만 나오게 해줌
    // val keywords = mutableSetOf<String>()

    // "i"가 포함된 데이터만 추출 - Lassie, Jamie, Hawaiifiveo
    // indexof를 사용하며, < 0 이면 없는것, >= 면 있는것을 찾아줌
    // indexof는 없으면 음수를 리턴하기 때문
    println("i가 포함된 데이터 : ${list.filter({it.name.indexOf("i") >= 0})}")

    // 앞에 두개를 빼고 가져옴
    println(list.drop(2))
    // 2개만 가져옴
    println(list.take(2))
    // 1부터 3까지 데이터만 가져옴
    println(list.slice(1..3))

    // list에서 name만 추출해서 새로운 컬렉션 만들기
    println("${list.map({it.name})}")

    // 시퀀스 생성
    // 1씩 증가
    var sequence = generateSequence(1){it+1}

    // 시퀀스에서 5개만 출력
    println(sequence.take(5).toList())
}