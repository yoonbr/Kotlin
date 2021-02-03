package com.yoond.a0202listview

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // ListView 출력 코드 작성
        val listView = findViewById<ListView>(R.id.listView)

        // 출력할 데이터를 생성
        var ar = arrayOf<String>("아메리카노", "카페라떼", "카푸치노", "말차라떼", "아이스초코", "돌체라떼")

        // 어댑터 생성
        /*
        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, ar)
         */

        val adapter = ArrayAdapter.createFromResource(
            this, R.array.coffee, android.R.layout.simple_list_item_1)

        // ListView와 Adapter 연결
        listView.adapter = adapter

        listView.choiceMode = ListView.CHOICE_MODE_SINGLE
        listView.divider = ColorDrawable(Color.GREEN)
        listView.dividerHeight = 10
    }
}