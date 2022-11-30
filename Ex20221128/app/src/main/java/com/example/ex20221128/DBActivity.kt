package com.example.ex20221128

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.TextView

class DBActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dbactivity)

        val tvResult = findViewById<TextView>(R.id.tvResult)

        var loginId:String? = intent.getStringExtra("loginId")
        var loginPw:String? = intent.getStringExtra("loginPw")

        Log.d("loginId->",loginId.toString())
        Log.d("loginPw->",loginPw.toString())

        if(loginId =="kyj"&&loginPw=="1234"){
            tvResult.text = "로그인 성공"
        }else{
            tvResult.text = "로그인 실패"
        }



    }
}