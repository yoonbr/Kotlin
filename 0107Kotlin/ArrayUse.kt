import java.util.*
import kotlin.Comparator

fun main(){
    // var ar:Array = arrayOf(<String>)
    // 배열은 처음에 값을 다 만들어주어야 함
    // 크기가 변경되지 않는 배열 생성
    var ar:Array<String> = arrayOf(
        "Java", "JavaScript", "Kotlin", "Swift"
    )

    // 전체 데이터 접근
    for(temp in ar){
        print("${temp}\t")
    }
    println()

    // 인덱스를 이용한 접근
    for(idx in ar.indices){
        print("${idx}:${ar[idx]}\t")
    }
    println()

    for(idx in 0..ar.size-1){
        print("${idx}:${ar.get(idx)}\t")
    }
    println()

    // iterator를 이용하는 방법
    val iterator = ar.iterator()
    while (iterator.hasNext()){
        print("${iterator.next()}\t")
    }
    println()

    // ar의 정렬된 결과를 저장하기
    var ar1 = ar.sorted()
    println(ar1)

    // ar의 내림차순 정렬된 결과를 저장하기
    for(temp in ar1) {
        print("${temp}\t")
    }
    println(ar1)

    val pl = arrayOf<String>(
        "javascript", "Java", "C&C++", "Python", "Swift", "C#",
        "Kotlin", "R"
    )
    // 알파벳 오름차순으로 출력되며 소문자는 대문자 뒤로 정렬됨
    // var ar2 = pl.sorted()
    // Lambda
    pl.sortBy({it.toUpperCase()})
    for(temp in pl){
        print("${temp}\t")
    }
    println()

    // num, name, price를 가지는 데이터 클래스
    data class Item(
        val num:Int, val name:String, val price:Int)
    var items = arrayOf<Item>(
        Item(1, "맥북 프로", 2000000),
        Item(2, "아이폰", 1500000),
        Item(3, "에어팟프로", 300000),
        Item(4, "아이패드", 1000000)
    )
        // Item에는 크기 비교하는 메소드가 없어서 정렬을 하려고 하면 예외가 발생
        // items.sort()
        // price를 가지고 오름차순 정렬
        /*
        items.sortBy({it.price})
        for(temp in items){
            print("${temp}\t")
        }
        println()
        */

    items.sortWith(
        Comparator<Item>{
            item1, item2 -> when{
                // 가격이 낮은 순서대로 정렬
                 /*
                item1.price > item2.price -> 1
                item1.price == item2.price -> 0
                else -> -1
                 */

                // 가격이 높은 순서대로 정렬
                // 부등호를 바꿔도 되고 -1과 1의 위치를 바꾸어주도 됨
                item1.price < item2.price -> 1
                item1.price == item2.price -> 0
                else -> -1
            }
        }
    )
    for(temp in items){
        print("${temp}\t")
    }
    println()
    // 2개의 1차원 배열 생성
    var pl1 = arrayOf<String>("Java", "Object-C")
    var pl2 = arrayOf<String>("Kotlin", "Swift")
    // 2개의 1차원 배열을 가지고 2차원 배열을 생성
    var matrix = arrayOf<Array<String>>(pl1, pl2)
    // Arrays는 배열을 가지고 작업을 수행해주는 static class
    println(Arrays.toString(matrix))
    // 1차원 배열로 전환
    var pAr = matrix.flatten()
    println(pAr)

}