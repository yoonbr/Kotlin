fun main(){
    // null을 저장할 수 있는 변수를 생성
    var str : String? = null

    // str의 길이(length)를 출력
    // ?.을 이용해서 프로퍼티를 호출하면
    // null이 아니면 작업을 수행하고 null이면 null을 리턴
    println(str?.length)
    // let은 null인 작업을 수행하지 않음
    str?.let{
        println("null이 아닐 때 수행")
    }

    // 앞의 내용이 null이면 ?: 이후의 동작을 수행
    var len : Int? = str?.length?:-1
    println("문자열의 길이:${len}")

    // !!는 null 이면 NullPointException 예외를 발생시킴
    // len = str!!.length

    // 형 변환 - null이면 NullPointException 예외 발생
    // var x = str as String

    var x = str as? String

    str = "문자열"
    println(str?.length)
    str?.let{
        println("null이 아닐 때 수행")
    }

    len = str?.length?:-1
    println("문자열의 길이:${len}")

    len = str!!.length
}