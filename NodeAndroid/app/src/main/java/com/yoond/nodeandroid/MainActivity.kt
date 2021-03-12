package com.yoond.nodeandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.StringBuilder
import java.net.HttpURLConnection
import java.net.URL
import java.nio.Buffer

class MainActivity : AppCompatActivity() {

    // 스레드 클래스 생성
    inner class DownloadThread : Thread() {
        override fun run() {
            val url:URL = URL("http://192.168.10.98:9393/item/itemlist")

            // 연결 객체 생성
            val con = url.openConnection() as HttpURLConnection

            // 옵션 설정
            con.requestMethod = "GET"
            con.useCaches = false
            con.connectTimeout = 30000

            // 스트림을 생성
            val br = BufferedReader(InputStreamReader(con.inputStream))
            // 문자열을 임시로 저장할 변수를 생성
            var sb = StringBuilder()

            // 문자열을 읽어서 저장
            while (true) {
                // 한 줄을 읽어오기
                var line = br.readLine()
                // null 이면 멈춤
                if (line == null) {
                    break
                }
                // null 이 아니면 가져옴
                sb.append(line)
            }

            br.close()
            con.disconnect()

            // 다운로드 받은 내용을 문자열로 변환
            val jsonString = sb.toString()

            val jsonObject = JSONObject(jsonString)

            // count의 내용을 정수로 가져오기
            val count = jsonObject.getInt("count")
            Log.e("count", "${count}")
            // 목록
            val list = jsonObject.getJSONArray("list")

            for(i in 0 until list.length()) {
                // 배열의 안에 객체를 하나씩 가져오기
                val obj = list.getJSONObject(i)
                val item = Item()
                item.itemid = obj.getInt("itemid")
                item.itemname = obj.getString("itemname")
                item.price = obj.getInt("price")
                item.description = obj.getString("description")
                item.pictureurl = obj.getString("pictureurl")
                item.updatedate = obj.getString("updatedate")
                Log.e("item", item.toString())
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 스레드 시작
        DownloadThread().start()

        }
    }
