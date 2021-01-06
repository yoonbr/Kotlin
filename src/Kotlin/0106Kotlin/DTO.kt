data class DTO(val num:Int, val name:String){
    var address : String = ""
}

fun main(){
    val dto1 = DTO(1, "bonnie")
    // 출력하는 메소드에 인스턴스 이름만 대입하면 toString 호출
    println(dto1.toString())
    println(dto1)
    val dto2 = DTO(2, "yoond")
    println("dto1과 dto2 비교 : ${dto1 == dto2}")
    println("dto1과 dto2 비교 : ${dto1.equals(dto2)}")

    val dto3 = DTO(1, "bonnie")
    dto3.address = "osan"
    // address는 비교 안함 - 생성자에 대입한 내용만 비교
    // == true : equals와 동일
    println("dto1과 dto3 비교 : ${dto1 == dto3}")
    // === false : 해시코드를 비교 생성자를 부르면 다름
    println("dto1과 dto3 비교 : ${dto1 === dto3}")
    println("dto1과 dto3 비교 : ${dto1.equals(dto3)}")

    val dto11 = DTO(11, "윤디")
    val dto12 = DTO(11, "윤디")

    // 해시코드 출력
    println("dto11:${dto11.hashCode()}")
    println("dto12:${dto12.hashCode()}")
    val dto13 = dto11
    println("dto13:${dto13.hashCode()}")
    dto11.address = "경기도"
    println(dto11.address)
    // 새로운 생성자를 이용해서 만들기 때문에 dto11의 영향을 받지 않음
    println(dto12.address)
    // 해시코드를 복사한 것이어서 dto11의 영향을 받게 됨
    println(dto13.address)

    // data 클래스로 만들면
    // hashCode, equals, toString 그리고 copy 메소드가 만들어짐
    // hashCode 메소드도 수정돼서 Primary Constructor의 매개변수 들의 조합으로 해시코드를 생성
    // 생성자를 호출할 때 동일한 데이터를 대입하면 실제 메모리 공간은 따로 생성이 되지만
    // 해시코드는 동일하게 리턴
    // Java에서 실제 메모리 영역의 해시코드를 출력할 때는
    // System 클래스의 identityHashcode라는 메소드로 실제 해시코드를 리턴

    // copy는 현재 데이터를 복제해서 새로운 공간에 만들어줌

    val dto21 = DTO(100, "마윈")
    val dto22 = dto21
    val dto23 = dto21.copy()
    val dto24 = dto21.copy(99)
    println(dto21) // 100 마윈
    println(dto22) // 100 마윈
    println(dto23) // 100 마윈
    println(dto24) // 99 마윈

    // 실제 메모리 영역의 해시코드 출력
    println(System.identityHashCode(dto21))
    println(System.identityHashCode(dto22)) // dto21과 동일
    println(System.identityHashCode(dto23)) // dto21과 다름
    println(System.identityHashCode(dto24)) // dto21과 다름

}