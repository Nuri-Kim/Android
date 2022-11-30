package com.example.ex20221129

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val etId = findViewById<EditText>(R.id.etId)
        val etPw = findViewById<EditText>(R.id.etPw)
        val btnLogin = findViewById<Button>(R.id.btnLogin)

        btnLogin.setOnClickListener {
            var userId = etId.text.toString()
            var userPw = etPw.text.toString()

            if (userId == "김누리" && userPw == "1234") {
                //로그인 성공
                setResult(RESULT_OK, intent)
            } else {
                //로그인 실패
                setResult(RESULT_CANCELED, intent)
            }

            finish()
        }


    }
}