package com.example.ex20221202

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient

class Fragment1 : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_1, container, false)

        // WebView 찾아오기
        val wv = view.findViewById<WebView>(R.id.wv)

        // sharedPreferences 가져오기 ( 이미 만들어진 mySPF 를 불러옴 )
        val spf = requireActivity().getSharedPreferences("mySPF", Context.MODE_PRIVATE)



        // WebView 띄우는 순서
        // 1. 주소 준비
        val url = spf.getString("url","http://www.google.com")
        // 2. 설정 변경 (Java Script 사용 가능하도록 허용 - WebView 는 JS 지원 안함)
        val ws = wv.settings
        ws.javaScriptEnabled = true // JavaScript 허용
        // 3. WebView Client 설정
        wv.webViewClient = WebViewClient()
        // 4. WebView 에 주소 적용

        // Default Value 있음
        wv.loadUrl(url!!)


        return view
    }


}