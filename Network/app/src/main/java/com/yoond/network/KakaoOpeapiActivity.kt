package com.yoond.network

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.renderscript.ScriptGroup
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_kakao_opeapi.*
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.StringBuilder
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder
import java.nio.Buffer

class KakaoOpeapiActivity : AppCompatActivity() {

    // 스레드의 넘겨준 데이터를 화면에 출력하는 역할을 수행
    val handler = object: Handler(Looper.getMainLooper()){
        override fun handleMessage(msg: Message) {
            // 11. 스레드가 전송해준 데이터 가져오기
            val result = msg.obj as String
            // 데이터 출력
            resultView.text = result

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kakao_opeapi)

        object:Thread(){
            override fun run(){
                // 1. 2개의 파라미터를 전송하는 kakao open API url 생성
                // target, title 안넣고 query만 생성
                // 파라미터는 인코딩 필수
                val addr = "https://dapi.kakao.com/v3/search/book?query=" +
                        URLEncoder.encode("안드로이드", "utf-8") + "&size=50"
                // 다운로드 받을 URL 생성
                val url = URL(addr)

                // 2.URLConnection 생성
                val con = url.openConnection() as HttpURLConnection

                // 3. 옵션 설정
                con.connectTimeout = 30000
                con.useCaches = false
                // 설정 안하면 기본값이 get
                con.requestMethod = "GET"
                con.setRequestProperty("Authorization", "KakaoAK 9b88c32462f5aea84392d09856a72bbb")

                // 4. 문자열을 받아오기 위한 인스턴스를 생성
                val sb = StringBuilder()
                val br = BufferedReader(InputStreamReader(con.inputStream))
                // 5. 문자열을 읽어서 sb에 추가하기
                while (true){
                    val line = br.readLine()
                    if(line == null){
                        break
                    }
                    sb.append(line)
                }
                // 6. 정리
                br.close()
                con.disconnect()

                // 데이터가 제대로 오는지 확인 테스트
                // Log.e("문자열", sb.toString())

                // 7. JSON Parsing
                if(TextUtils.isEmpty(sb.toString())){
                    Toast.makeText(this@KakaoOpeapiActivity, "쿼리의 횟수가 초과되거나 네트워크에 이상이 있습니다.",
                    Toast.LENGTH_LONG).show()
                    return
                } else {
                    // 문자열을 JSONObject로 변환
                    val data = JSONObject(sb.toString())
                    // JSONObject = { } JSONArray = [ ]
                    val documents = data.getJSONArray("documents")
                    var result : String = ""
                    // 8. 반복문 수행
                    // { } 안에 내용 가져오기
                    for (i in 0 until documents.length()){
                        // 배열 안의 요소를 JSONObject 로 가져오기
                        val document = documents.getJSONObject(i)
                        // title, price, authors 가져오기
                        val authors = document.getString("authors")
                        val title = document.getString("title")
                        val price = document.getInt("price")
                        result = result + "제목 : $title 저자 : $authors 가격 : $price\n"
                    }

                    // 9. 핸들러에게 전송할 메시지를 생성
                    val msg = Message()
                    msg.obj = result
                    // 10. 핸들러에게 메시지 전송 -> Handler로 가기
                    handler.sendMessage(msg)
                }
            }
        }.start()
    }
}