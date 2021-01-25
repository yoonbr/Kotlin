package com.yoond.a0123note

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        webView.loadUrl("http://www.naver.com")
        webView.webViewClient = WebViewClient()

        // URL로 이동
        btngo.setOnClickListener {
            val url = addr.text.toString() // text를 String으로 변환
            webView.loadUrl(url) // 위의 url을 읽어오기
        }
        // 뒤로 가기
        btnback.setOnClickListener {
            webView.goBack()
        }
        btnlocal.setOnClickListener {
            webView.goForward()
        }
        btnforward.setOnClickListener {
            // app 내의 assets 디렉토리의 test.html을 로드
            webView.loadUrl("file:///android_asset/test.html")
        }

    }
}