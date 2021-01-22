package com.yoond.androidpractice2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.io.BufferedReader
import java.io.StringReader
import java.lang.NumberFormatException

class MainActivity : AppCompatActivity() {

    // listof = 최초의 정의된 값만 가지고 사용, 수정 안됨 = val로 지정해도 무방
    // TopLevel에서 선언
    val productlist = listOf(
        Product("가", 1),
        Product("나", 2),
        Product("다", 3),
        Product("라", 4),
        Product("마", 5)
    )

    var productlist2 = mutableListOf<Product>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 출력 하면 logcat에 string으로 출력됨
        //var product = Product()
        //println(product)

        // println(productlist)
        // 한 줄씩 출력
        // productlist.forEach{product -> println(product)}

        // mutableListOf = product2 객체를 생성해서 바로 입력
        productlist2.add(Product("바", 6))
        productlist2.add(Product("차", 10))
        productlist2.add(Product("아", 8))
        productlist2.add(Product("사", 7))
        productlist2.add(Product("자", 9))
        productlist2.add(Product("아", 11))
        productlist2.add(Product("바", 2))

        // product의 이름 순으로 정렬
        // productlist2.sortWith(compareBy({product -> product.name}))

        // 이름이 같고 가격이 다른 객체를 이름, 가격 순으로 정렬
        productlist2.sortWith(compareBy({product -> product.name}, {product -> product.price}))
        // 출력
        productlist2.forEach{product -> println(product)}

        // filter - 문자열 filter 는 contains 사용
        // "바"라는 이름을 가진 product만 출력
        productlist2.filter { product -> product.name.contains("바") }
                // method chaining 기법으로 출력 : 순서를 고려해서 작업
            .forEach{product->println(product)}

        // lambda : product를 생략
        productlist2.filter { it.name.contains("바") }
            .forEach{println(it)}

        // 해당 문자열에서 반환되는 값 가져오기
        val reader = BufferedReader(StringReader("1 ==> D:\\lec\\Kotlin\\KotlinForBeginner\\lecture"))

        // numbers를 list로 가져옴
        val numbers = GetStringNumber(reader)

        println(numbers)

        println("Numbers => $numbers")
        println("Numbers Count => ${GetCountNumberInString(numbers)}")

    }

    // numbers를 list, int 값으 로 가져오는 함수
    // null이 들어올 수도 있다는 것을 알려주기 위해 ?를 삽입
    fun GetStringNumber(reader: BufferedReader):List<Int?>{
        // java의 arraylist를 가져와서 사용할 수 있음
        val result = ArrayList<Int?>()
        // 문자열에서 숫자만 추출
        for(char in reader.readLine().toCharArray()){
            // try 구문을 이용하여 숫자를 판별
            try{
                // 반환된 값을 toString으로 변환 후 Integer로 변환해서 저장
                // char 상태로 변환하면 ASCII값으로 나오기 때문에
                // 문자열로 변환 - result값이 Int이므로 다시 toInt
                result.add(char.toString().toInt())
                // 숫자가 아니면 null 삽입
                // 숫자가 아닌 문자들은 다 null로 출력됨
            }catch (e:NumberFormatException){
                result.add(null)
            }
        }
        return result
    }

    // null값이 들어올 수 있게 타입을 변경해주어야 함 null safety <Int?>
    // 특수한 상황을 미리 코딩할때 설정해두어야 함
    fun GetCountNumberInString(list:List<Int?>):Int{
//        var cntNum = 0
//        var cntNoNum = 0
//        // list에서 number를 가져옴
//        for (number in list) {
//            // 받아오는 값이 null이 아니면
//            if(number != null) {
//                // cntNum의 값을 증가시킴
//                cntNum++
//
//                // 받아오는 값이 null 이면
//            }else{
//                // cntNoNum의 값을 증가시킴
//                cntNoNum++
//            }
//        }

        // null이 아닌 값만 count로 반환
        var cntNum = list.filterNotNull().count()

        var cntNoNum = list.count()-cntNum

        println("cntnonumber => $cntNoNum")
        return cntNum
    }
}