package com.example.ex20221124

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // 1. View 들의 id 값 받아오기

            val etEmail = findViewById<EditText>(R.id.etEmail)
            val etPw = findViewById<EditText>(R.id.etPw)
            val btnLogin = findViewById<Button>(R.id.btnLogin)

        // 2. Button 이벤트 달아주기

        btnLogin.setOnClickListener {
        // 2-1. EditText에 적혀있는 email, password 값 가져오기
            var email = etEmail.text.toString()
            var pw = etPw.text.toString()

        // 2-2. 가져온 email, pw 가 smhrd@smhrd.or.kr, qwer1234 와 비교
            if(email=="smhrd@smhrd.or.kr"&&pw=="qwer1234"){ //  로그인 성공

                Toast.makeText(this,"로그인 성공",Toast.LENGTH_SHORT).show()
            }else{ // 로그인 실패
                Toast.makeText(this,"로그인 실패",Toast.LENGTH_SHORT).show()
            }
        }
















    }
}