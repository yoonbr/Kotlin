fun main(args: Array<String>){
    // 데이터를 변경할 수 있는 String의 List를 생성
    val os = mutableListOf<String>()

    // 데이터를 4개 삽입
    os.add("Lassie")
    os.add("hawaiifiveo")
    os.add("dexter")
    os.add("jamie")

    // 전체 데이터를 순회해서 출력
    for(temp in os){
        print("${temp}\t")
    }
    println()

    var start = 0
    // 앞의 2개만 출력
    for(i in start..start+1){
        print("${os.get(i)}\t")
    }
    println()
    start = start + 2

    // 다음 2개 출력
    for(i in start..start+1){
        print("${os.get(i)}\t")
    }
    println()
    start = start + 2

    // 중간 데이터를 수정하거나 삭제
    os.set(1, "Blair")
    os.removeAt(2)
    println(os)
}