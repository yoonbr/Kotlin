package com.yoond.network

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_item_insert.*
import kotlinx.android.synthetic.main.activity_main.*
import java.io.BufferedReader
import java.io.ByteArrayOutputStream
import java.io.DataOutputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.nio.Buffer
import java.text.SimpleDateFormat
import java.util.*

class ItemInsertActivity : AppCompatActivity() {
    val handler = object:Handler(Looper.getMainLooper()){
        override fun handleMessage(msg: Message) {

        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_insert)

        // 아이템 삽입을 눌렀을 때 수행할 코드
        btnInsert.setOnClickListener {
            // 유효성 검사

            // 입력한 내용 가져오기
            val itemname = itemnameinput.text.toString()
            val price = priceinput.text.toString()
            val description = descriptioninput.text.toString()

            if (TextUtils.isEmpty(itemname.trim())){
                // 아이템 이름이 공백일때 알림을 하고 다시 돌아가게 하기
                Toast.makeText(this, "아이템 이름을 입력하세요.", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            if (TextUtils.isEmpty(price.trim())){
                // 아이템 이름이 공백일때 알림을 하고 다시 돌아가게 하기
                Toast.makeText(this, "가격을 입력하세요.", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            if (TextUtils.isEmpty(description.trim())){
                // 아이템 이름이 공백일때 알림을 하고 다시 돌아가게 하기
                Toast.makeText(this, "설명을 입력하세요.", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            // 업로드하는 스레드를 생성하고 시작
            object : Thread(){
                override fun run(){
                    // 다운로드 받을 URL을 생성하고 연결
                    val url = URL("http://cyberadam.cafe24.com/item/insert")

                    // 연결
                    val con = url.openConnection() as HttpURLConnection

                    // 파라미터 이름 만들기 - 파일은 제외
                    val dataName = arrayOf("itemname", "price", "description", "updatedate")

                    // 파라미터 만들기
                    val date = Date()
                    val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
                    val updatedate = simpleDateFormat.format(date)

                    val data = arrayOf(itemname, price, description, updatedate)

                    // 파일 업로드가 있을 때는 라인의 끝과 구분해주기 위한 코드가 필요
                    val lineEnd = "\r\n"
                    val boundary = UUID.randomUUID().toString()

                    // 연결 객체 옵션
                    con.connectTimeout = 30000 // 30초 동안 접속을 시도
                    con.useCaches = false // 캐싱을 하지 않음
                    con.requestMethod = "POST"

                    // 파일 업로드가 있을 때 설정
                    // 파일
                    con.setRequestProperty("ENCTYPE", "multipart/form-data")
                    con.setRequestProperty("Content-Type", "multipart/form-data;boundary=${boundary}")

                    // 파라미터 전송
                    val delimiter = "--${boundary}${lineEnd}" // 구분자 생성
                    // 전송할 파라미터 생성
                    val postDataBuilder = StringBuffer()
                    for(i in data.indices){
                        postDataBuilder.append(delimiter)
                        postDataBuilder.append("Content-Disposition: form-data;" +
                                " name=\"${dataName[i]}\"${lineEnd}${lineEnd}${data[i]}${lineEnd}")
                    }

                    // 파일 이름을 생성
                    val fileName:String? = "starbucks.png"
                    // 파일 파라미터 추가
                    if(fileName != null){
                        postDataBuilder.append(delimiter)
                        postDataBuilder.append("Content-Disposition: form-data;" +
                                "name=\"pictureurl\";filename=\"${fileName}\"${lineEnd}")
                    }

                    // 파라미터 전송 - 아직 파일은 안받은 상태
                    val ds = DataOutputStream(con.outputStream)
                    ds.write(postDataBuilder.toString().toByteArray())

                    // 파일 전송
                    if(fileName != null){
                        ds.writeBytes(lineEnd)

                        // drawable 디렉토리에 저장판 파일 읽기
                        val starbucks = resources.getDrawable(R.drawable.starbucks, null)
                        val bitmap = (starbucks as BitmapDrawable).bitmap
                        val stream = ByteArrayOutputStream()
                        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
                        val buffer:ByteArray = stream.toByteArray()
                        // 파일을 웹 서버에게 업로드
                        ds.write(buffer, 0, buffer.size)

                        ds.writeBytes(lineEnd)
                        ds.writeBytes(lineEnd)
                        ds.writeBytes("--${boundary}--${lineEnd}")
                    } else {
                        ds.writeBytes(lineEnd)
                        ds.writeBytes("--${boundary}--${lineEnd}")
                    }
                    ds.flush()
                    ds.close()

                    // 응답 받기
                    val sb = StringBuilder()
                    val br = BufferedReader(InputStreamReader(con.inputStream))
                    while (true){
                        val line = br.readLine()
                        if(line == null){
                            break
                        }
                        sb.append(line)
                    }
                    br.close()
                    con.disconnect()

                    // 결과 확인
                    Log.e("결과", sb.toString())

                }
            }.start()
        }
    }
}