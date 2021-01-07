fun main() {
    val str = "Hello Python"
    // 공백 뒤의 문자를 추출해서 출력
    // 문자열을 분리할 때는 위치를 가지고 하는 방법과 특정 패턴을 이용하는 방법
    // substring, split 사용
    println(str.substring(6, 12))

    // 안나오면 역슬래시 붙임
    var ar = str.split(" ")
    println(ar[ar.size - 1])

    // 1. 공백의 위치 찾기
    // 있는 문자를 집어넣으면 위치가, 없는 문자를 집어넣으면 음수가 나타남
    var idx = str.indexOf(" ")
    // println(idx)
    // 공백을 포함해서 나타나기 때문에 idx뒤에 +1을 해줌
    if(idx >= 0){
        var result = str.substring(idx + 1)
        println(result)
    }
}