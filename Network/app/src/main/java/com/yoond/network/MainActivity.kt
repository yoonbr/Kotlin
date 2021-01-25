package com.yoond.network

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_html.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 직접 뷰를 찾아 변수로 생성
        // val btnTextDownload = fintViewById<Button>(R.id.btnTextDownload)
        // val btnImgDownload = fintViewById<Button>(R.id.btnImgDownload)

        btnTextDownload.setOnClickListener {
            val intent = Intent(this, TextDownloadActivity::class.java)
            startActivity(intent)
            // startActivity(Intent(this, TextDownloadActivity::class.java))
        }

        btnImgDownload.setOnClickListener {
            val intent = Intent(this, ImageDownloadActivity::class.java)
            startActivity(intent)
            // startActivity(Intent(this, TextDownloadActivity::class.java))
        }

        btnImgDownload.setOnClickListener {
            val intent = Intent(this, ImageDownloadActivity::class.java)
            startActivity(intent)
            // startActivity(Intent(this, TextDownloadActivity::class.java))
        }

        btnHtmlParsing.setOnClickListener {
            val intent = Intent(this, HtmlActivity::class.java)
            startActivity(intent)
            // startActivity(Intent(this, TextDownloadActivity::class.java))
        }

        btnDomParsing.setOnClickListener {
            val intent = Intent(this, DomActivity::class.java)
            startActivity(intent)
            // startActivity(Intent(this, TextDownloadActivity::class.java))
        }

        btnKakaoOpenAPI.setOnClickListener {
            val intent = Intent(this, KakaoOpeapiActivity::class.java)
            startActivity(intent)
        }

        btnItemDetail.setOnClickListener {
            var intent = Intent(this, ItemDetailActivity::class.java)
            startActivity(intent)
        }

        btnItemInsert.setOnClickListener {
            var intent = Intent(this, ItemInsertActivity::class.java)
            startActivity(intent)
        }
    }
}