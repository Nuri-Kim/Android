package com.example.fullstackapplication.tip

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.fullstackapplication.R

class WebViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)

        // 받아온 url 값을 꺼내서 webView 에 적용

        val wv = findViewById<WebView>(R.id.wv)

        val url = intent.getStringExtra("url")

        // 설정 변경 (js 지원)
        val ws = wv.settings
        ws.javaScriptEnabled = true

        // 웹뷰 클라이언트 적용
        wv.webViewClient = WebViewClient()

        // intent 값 전달받음
        wv.loadUrl(url!!)





    }
}