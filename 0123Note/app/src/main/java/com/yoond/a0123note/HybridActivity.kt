package com.yoond.a0123note

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import android.webkit.JavascriptInterface
import android.webkit.WebViewClient
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_hybrid.*
import kotlinx.android.synthetic.main.activity_main.*

class HybridActivity : AppCompatActivity(){
    class AndroidJavaScriptInterface(aContext: Context){
        private var mContext:Context? = null
        private val handler:Handler = Handler(Looper.getMainLooper())

        @JavascriptInterface
        fun showToastMessage(aMessage:String){
            handler.post(Runnable {
                Toast.makeText(mContext, aMessage, Toast.LENGTH_LONG).show()
            })
        }

        init{
            mContext = aContext
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hybrid)

        // 웹뷰 설정
        webview.webViewClient = WebViewClient()
        // 리다이렉트 될 때 JavaScript 사용 가능하게 해줌
        webview.settings.javaScriptEnabled = true
        webview.addJavascriptInterface(
                AndroidJavaScriptInterface(this),"MyApp")
        webview.loadUrl("http://172.30.1.21:930/androidweb")

        // 버튼 클릭, toString 필수
        sendmsg.setOnClickListener {
            val sendmessage = sendtxt.text.toString()
            // javascript 함수 실행
            webview.loadUrl("javascript:showDisplayMessage('$sendmessage')")

        }
    }
}
