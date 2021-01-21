package com.yoond.a0121datause

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context) : SQLiteOpenHelper(
    context, "memodb", null, 1){
    override fun onCreate(db: SQLiteDatabase?) {
        // 테이블 생성
        val memoSQL = "create table tb_memo (_id integer primary key autoincrement, " +
                "title text, content text)"
        db?.execSQL(memoSQL)
    }

    // 앱이 업그레이드 될 때 호출되는 메소드
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // 이전 테이블을 삭제하고 다시 생성
        db?.execSQL("drop table tb_memo")
        onCreate(db)
    }

}