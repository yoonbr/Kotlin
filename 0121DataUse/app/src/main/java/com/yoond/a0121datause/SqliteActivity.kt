package com.yoond.a0121datause

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_sqlite.*

class SqliteActivity : AppCompatActivity() {

    fun clickNextTask(){
        // 키보드 관리 객체 생성
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE)
            as InputMethodManager
        // 키보드 숨기기
        imm.hideSoftInputFromWindow(editTitleView.windowToken, 0)
        imm.hideSoftInputFromWindow(editContentView.windowToken, 0)

        // edit 초기화
        editTitleView.setText("")
        editContentView.setText("")

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sqlite)

        btnCreate.setOnClickListener{
            // 입력한 내용을 가져오기
            val title = editTitleView.text.toString()
            val content = editContentView.text.toString()

            if(TextUtils.isEmpty(title)){
                Toast.makeText(this, "제목을 입력해주세요.", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            if(TextUtils.isEmpty(content)){
                Toast.makeText(this, "내용을 입력해주세요.", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            // DBHelper 객체 생성
            val helper = DBHelper(this)
            // 데이터베이스 열기
            val db = helper.writableDatabase

            // 삽입하는 SQL 실행
            /*
            db.execSQL("insert into tb_memo(title, content) values(?,?)",
                arrayOf<String>(title,content))

             */

            // ContentValues 이용
            val row = ContentValues()
            row.put("title", title)
            row.put("content", content)
            db.insert("tb_memo", null, row)

            db.close()
            clickNextTask()
            Toast.makeText(this, "삽입 성공", Toast.LENGTH_LONG).show()

        }

        // 읽기 버튼의 클릭 이벤트 처리
        btnRead.setOnClickListener{
            val keyword = editTitleView.text.toString()

            val intent = Intent(this, ReadActivity::class.java)
            intent.putExtra("keyword", keyword)
            startActivity(intent)
            clickNextTask()
        }

        btnUpdate.setOnClickListener{
            // 입력한 내용을 가져오기
            val title = editTitleView.text.toString()
            val content = editContentView.text.toString()

            if(TextUtils.isEmpty(title)){
                Toast.makeText(this, "제목을 입력해주세요.", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            if(TextUtils.isEmpty(content)){
                Toast.makeText(this, "내용을 입력해주세요.", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            // DBHelper 객체 생성
            val helper = DBHelper(this)
            // 데이터베이스 열기
            val db = helper.writableDatabase

            // 수정하는 쿼리 실행
            db.execSQL("update tb_memo set content = ? where title = ?",
            arrayOf<String>(content, title))

            db.close()

            clickNextTask()

            Toast.makeText(this, "수정 성공", Toast.LENGTH_LONG).show()
        }

        btnDelete.setOnClickListener{
            // 입력한 내용을 가져오기
            val title = editTitleView.text.toString()

            if(TextUtils.isEmpty(title)){
                Toast.makeText(this, "제목을 입력해주세요.", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            // DBHelper 객체 생성
            val helper = DBHelper(this)
            // 데이터베이스 열기
            val db = helper.writableDatabase

            // 수정하는 쿼리 실행
            db.execSQL("delete from tb_memo where title = ?",
                arrayOf<String>(title))

            db.close()

            clickNextTask()

            Toast.makeText(this, "삭제 성공", Toast.LENGTH_LONG).show()
        }
    }
}