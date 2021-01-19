package com.yoond.androidpractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // textview에 내용 입력후 button을 클릭하면 edittext에 출력
       button.setOnClickListener{
            textView.text = editText.text
        }
        // lambda로 표현
        // v = View 객체의 타입
        button.setOnClickListener { v -> textView.text = editText.text }
    }
}