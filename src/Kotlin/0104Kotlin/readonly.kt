var rw : Int = 10 // 변경 가능한 변수를 생성
    get(){
        println("rw의 getter 호출")
        return field // 원래의 값을 리턴
    }
    set(value : Int){
        println("rw의 setter 호출")
        field = value
    }
// val은 setter가 없는 것
// getter를 재정의해서 원래의 값과 다른 값 사용이 가능
var b : Boolean = true
val r : String ="Hello world"
   get(){
       if(b == true){
           return field.toUpperCase()
       } else {
           return field.toLowerCase()
       }
   }

// val 앞에 const를 추가하면 getter를 overriding 할 수 없음
// 초기값만 사용해야 함
//const val constant : String = "Hi"
//get() {}

// String 대신에 str을 사용하는 것이 가능
typealias str = String

fun main(args:Array<String>) {
    var temp : str = "Hello Typealias"

    rw = 100 // rw의 setter를 호출
    println(rw) // rw의 getter를 호출

    // r에 값을 대입하는 것은 안됨
    // r = "Hi"

    // b값에 의해서 r의 값이 다른 내용을 출력
    b = false
    println(r)
    b = true
    println(r)

    // raw String
    var msg = "Hello\nKotlin"
    println(msg)

    // 이런 형태를 raw string 이라고 함
    msg = """Hello
        Kotlin
    """
    println(msg)

    val n1 = 10
    var n2 = 20

    // 10 + 20 = 30 이런 형태로 출력
    // 문자열 템플릿 - 문자열 안에 수식이나 변수를 출력하는 것
    msg = "${n1} + ${n2} = ${n1 + n2}"
    println(msg)

    // 숫자를 이용해서 문자 생성
    var ch : Char = 65.toChar()
    println(ch) // A
    ch ='\uD55C' // 한이라는 글자를 유니코드로 삽입
    println(ch)
    ch = '\\' // 제어문자를 이용해서 삽입
    println(ch)

    var x : Int = 20
    var y : Int = 3

    x %= y
    println("결과는 ${x}")




}


