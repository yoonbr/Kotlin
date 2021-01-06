enum class Priority(val no:Int){
    MAX(10), NORMAL(5), MIN(1)
}

fun main(){
    var priority : Priority = Priority.MAX
    println("이름:${priority.name} 값:${priority.ordinal}")

    priority = Priority.NORMAL
    println("이름:${priority.name} 값:${priority.ordinal}")

    priority = Priority.MIN
    println("이름:${priority.name} 값:${priority.ordinal}")
    println("이름:${priority.no}")

}