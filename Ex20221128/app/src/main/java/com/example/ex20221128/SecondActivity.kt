package com.example.ex20221128

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)


        val btnPre = findViewById<Button>(R.id.btnPre)
        val cl = findViewById<ConstraintLayout>(R.id.cl)
        // 보내준 intent 안에 color 키 값을 통해 색깔 꺼내기

        var color:String? = intent.getStringExtra("color")

        Log.d("받아온값",color.toString())

        cl.setBackgroundColor(Color.parseColor(color))



        btnPre.setOnClickListener {
            // btnPre 클릭 시 FirstActivity 로 이동

//            var intent = Intent(this@SecondActivity,
//                FirstActivity::class.java)
//
//            startActivity(intent)

            finish() // stack 구조에서 out 시키기



        }
    }
}