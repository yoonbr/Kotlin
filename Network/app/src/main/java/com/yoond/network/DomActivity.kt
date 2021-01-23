package com.yoond.network

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_html.*
import java.io.BufferedReader
import java.io.ByteArrayInputStream
import java.io.InputStreamReader
import java.lang.StringBuilder
import java.net.HttpURLConnection
import java.net.URL
import java.nio.Buffer
import javax.xml.parsers.DocumentBuilder
import javax.xml.parsers.DocumentBuilderFactory

class DomActivity : AppCompatActivity() {

    // 화면 갱신을 위한 핸들러
    val handler = object: Handler(Looper.getMainLooper()){
        override fun handleMessage(msg: Message) {
            // 데이터 가져오기
            val result = msg.obj as String
            resultView.text = result
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dom)

        object:Thread(){
            override fun run(){
                Log.e("스레드", "시작")
                // 데이터 다운로드
                val url = URL("https://www.kisa.or.kr/jsp/util/rss.jsp?b_No=4")
                val con = url.openConnection() as HttpURLConnection

                con.connectTimeout = 30000
                con.useCaches = false

                val br = BufferedReader(InputStreamReader(
                    con.inputStream
                ))

                val sb = StringBuilder()

                Log.e("위치", "다운받기 전")

                while(true){
                    val line = br.readLine()
                    if(line == null){
                        break
                    }
                    sb.append(line)
                }
                br.close()
                con.disconnect()

                Log.e("xml", sb.toString())

                if (!TextUtils.isEmpty(sb.toString())){
                    val factory = DocumentBuilderFactory.newInstance()
                    val builder = factory.newDocumentBuilder()
                    // 다운로드 데이터를 스트림으로 변환
                    val istream = ByteArrayInputStream(sb.toString().toByteArray(charset("utf-8")))
                    // 메모리에 펼치기
                    val doc = builder.parse(istream)
                    // 루트 찾기
                    val root = doc.documentElement
                    // 필요한 데이터 찾기
                    val items = root.getElementsByTagName("title")

                    // 출력 내용 만들기
                    var result = ""

                    for (idx in 0 until items.length){
                        // 데이터 찾아오기
                        val item = items.item(idx)
                        // 태그 안의 내용을 찾아오기
                        val text = item.firstChild
                        val title = text.nodeValue
                        result = result + title + "\n"
                    }

                    val msg = Message()
                    msg.obj = result
                    handler.sendMessage(msg)
                }
            }
        }.start()


    }
}