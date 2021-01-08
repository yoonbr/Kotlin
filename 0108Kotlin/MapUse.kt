fun main() {
    // 변경 가능한 Map을 생성
    // null 저장시 Any?
    val map = mutableMapOf<String, Any>()

    // 번호와 이름을 저장
    map.put("num", 1)
    map.put("name", "bonnie")

    // 번호와 이름을 출력
    println("번호:${map.get("num")}")
    println("이름:${map["name"]}")

    // 주소를 추가
    map.put("address", "경기도")

    // 전체 데이터 출력
    for (key in map.keys) {
        println("${key}:${map[key]}")
    }
}