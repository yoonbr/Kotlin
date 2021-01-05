// 매개변수의 개수를 설정하지 않은 함수
fun disp(vararg ar:Any){
    println("ar의 자료형은 배열 ? ${ar is Array}")
    for(element in ar){
        println(element)
    }
}

// 피보나치 수열을 재귀로 만들기
// tailrec가 붙으면 소스 코드는 재귀로 만들었지만
// 컴파일을 하고 나면 이전에 만든 것 처럼 반복문으로 변경됨
tailrec fun fiboRecursion(n:Int):Int{
    if(n==1 || n==2)
        return 1
    else
        return fiboRecursion(n-1) + fiboRecursion(n-2)
}


fun main(args:Array<String>){
    disp("Java")
    disp("Java", "Kotlin")
    disp("Java", "Android")
   // println(fiboRecursion(100))

    var myString = MyString()
    myString add "Hello"
    myString add "Kotlin"
            println(myString.string)

    var msg = "Hello" add "Kotlin"
}

// infix 함수
infix fun String.add(other:String):String{
    return this + other
}

// var msg = "Hello" add "Kotlin"

class MyString{
    var string =""
    infix fun add(other:String){
        this.string = this.string + other

    }
}
// main에 작성
//var myString = MyString()
//myString add "Hello"
//myString add "Kotlin"
//        println(myString.string)