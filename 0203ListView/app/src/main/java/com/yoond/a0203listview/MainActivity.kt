package com.yoond.a0203listview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.SparseBooleanArray
import android.view.View
import android.widget.*

class MainActivity : AppCompatActivity() {
    var data : MutableList<String>? = null
    var adapter : ArrayAdapter<String>? = null
    var listView : ListView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 데이터 생성
        data = mutableListOf<String>(
                "Americano", "CafeLatte", "Greentea", "IceChoco",
                "DolcheLatte", "CaramelLatte", "HotChoco"
        )

        // Adapter 생성
        adapter = ArrayAdapter<String> (
                this, android.R.layout.simple_list_item_multiple_choice,
                data!!)

        // ListView를 가져와서 출력 설정
        listView = findViewById<ListView>(R.id.listView)
        listView?.adapter = adapter
        listView?.choiceMode = ListView.CHOICE_MODE_MULTIPLE

        // listView에서 항목을 클릭했을 때 처리
        listView?.onItemClickListener = AdapterView.OnItemClickListener{
            adapterView, view, i, l ->
            // adapterView : listView
            // view1이 선택한 항목 뷰
            // i는 선택한 항목의 인덱스 - 중요
            // l은 항목 뷰의 아이디

            // 배열이나 List에서 선택한 데이터 찾아오기
            val item = data!![i]
            Log.e("choose data", item.toString())
        }

        // 추가버튼을 클릭했을 때 처리를 위한 코드
        val addBtn = findViewById<Button>(R.id.add)
        addBtn.setOnClickListener {
            // EditText에 입력된 내용 가져오기
            val input = findViewById<EditText>(R.id.newitem)
            val newItem = input.text.toString().trim()

            if(newItem.length > 0) {
                data!!.add(newItem)
                // 데이터가 갱신된 사실을 ListView에 통보
                adapter!!.notifyDataSetChanged()
                input.setText("")
            }
        }
        // 삭제 버튼을 누르면 동작하는 코드
        val delBtn = findViewById<Button>(R.id.delete)
        delBtn.setOnClickListener {
            // 리스트 뷰에서 선택된 항목의 인덱스 가져오기
            // SparseBooleanArray로 리턴을 해주는데
            // 각 인덱스에 선택여부를 Boolean 타입으로 저장하는 배열
            val sb : SparseBooleanArray? = listView?.checkedItemPositions
            if(sb != null && sb.size() > 0){
                // 뒤에서부터 앞으로 접근
                for(i in (listView?.count as Int)-1 downTo 0){
                    // true 인텍스만 삭제
                    if(sb[i] == true){
                        data!!.removeAt(i)
                    }
                }
                adapter?.notifyDataSetChanged()
                listView?.clearChoices()
            }
        }


    }
}