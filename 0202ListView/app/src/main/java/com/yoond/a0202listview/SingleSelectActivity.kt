package com.yoond.a0202listview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*

class SingleSelectActivity : AppCompatActivity() {

    // 출력할 데이터 List
    var data : MutableList<String>? = null
    // Adapter
    var adapter : ArrayAdapter<String>? = null
    // View
    var listView : ListView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_select)

        // 데이터 생성
        data = mutableListOf<String>("아메리카노", "라떼", "프라푸치노", "피지오")
        // 어댑터 생성
        adapter = ArrayAdapter<String>(this,
            android.R.layout.simple_list_item_single_choice, data!!)

        // listView를 가져오고 출력
        listView = findViewById<ListView>(R.id.listView)
        listView?.adapter = adapter
        listView?.choiceMode = ListView.CHOICE_MODE_SINGLE

        // 항목을 선택했을 때 수행될 내용
        listView?.onItemClickListener = AdapterView.OnItemClickListener{
            parent, view, position, id ->

            // 선택한 항목 찾아오기
            val item = data!![position]
            Toast.makeText(this@SingleSelectActivity, item, Toast.LENGTH_LONG).show()
            Log.e("item", item.toString())

        }

        // 추가버튼 클릭 이벤트 작성
        val addBtn = findViewById<Button>(R.id.btnadd)
        addBtn.setOnClickListener {
            // EditText 찾아오기
            val newItem = findViewById<EditText>(R.id.btnnewitem)
            val item = newItem.text.toString().trim()

            if(item.length > 0) {
                // 데이터를 추가하고 ListView를 다시 출력
                data!!.add(item)
                adapter!!.notifyDataSetChanged()
                newItem.setText("")
            }
        }

        // 삭제 버튼 클릭 이벤트 작성
        val delBtn = findViewById<Button>(R.id.btndelete)
        delBtn.setOnClickListener {
            // 체크된 인덱스 가져오기
            val pos = listView?.checkedItemPosition
            if(pos!! >= 0 && pos < data!!.size){
                data!!.removeAt(pos)
                listView?.clearChoices()
                adapter?.notifyDataSetChanged()
            }
        }
    }
}