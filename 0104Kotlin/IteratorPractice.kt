fun main(args:Array<String>){
    for (idx in 1..9 step 2){
        println(idx)
    }
    // image1.png ~ image9.png 까지 출력
    // 하나씩 출력할때는 step 안써도됨
    for(idx in 1..9){
        println("image${idx}.png")
    }

    // 배열과 List 생성
    var ar = arrayOf<String>("aa", "bb", "cc", "dd")

    var list = listOf<String>("ee", "ff", "gg")

    // 인덱스를 idx에 대입
    for(idx in ar.indices){
        println(idx)
    }

    // 인덱스와 값을 idx에 대입
    for(idx in list.withIndex()){
        println(idx)
    }
}