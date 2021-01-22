package com.yoond.androidpractice2

// Product라는 class에 이름, 가격 추가
// Kotlin은 여러 개의 클래스 선언이 가능하지만, Java는 불가능
data class Product(var name:String="", var price : Int=0)

data class NoteBook(var name: String="", var price: Int=0)
