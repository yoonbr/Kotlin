fun main() {
   try {
       val ar = arrayListOf<String>("Java", "Kotlin")
       println(ar[2])
   }catch(e:Exception){
       // 예외 이유를 출력
       // finally 나 예외 처리 바깥 블럭으로 이동해서 다음 작업 수행
       println("예외 발생:${e.message}")
   }
    println("main의 마지막 문장")
}
