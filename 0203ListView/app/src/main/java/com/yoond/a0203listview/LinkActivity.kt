package com.yoond.a0203listview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient

class LinkActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_link)

        // 웹 뷰 찾아오기
        val webview = findViewById<WebView>(R.id.webView)

        // 웹 뷰 설정 - 리다이렉트된 URL 온 경우 크롬으로 열지 않고 webview로 열기
        webview.webViewClient = WebViewClient()
        var settings = webview.settings
        settings!!.javaScriptEnabled = true
        settings!!.builtInZoomControls = true

        // 이전 Activity에서 넘어온 데이터 가져오기
        val link = intent.getStringExtra("link")

        // 웹 뷰가 URL을 로드
        webview.loadUrl(link!!)


    }
}