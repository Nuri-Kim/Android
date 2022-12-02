package com.example.ex221130

import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout

class ExamActivity : AppCompatActivity() {

    lateinit var clExam : ConstraintLayout

    override fun onRestart() {
        super.onRestart()
        val sharedPreferences = getSharedPreferences("sp1",Context.MODE_PRIVATE)
        val color = sharedPreferences.getString("bgColor","white")

        clExam.setBackgroundColor(Color.parseColor(color))

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exam)

        // DataBase
        // - RDB (Relational DataBase )
        // - RDBMS ( Oracle, MySQL, MariaDB)
        // -> SQL : 쿼리문
        // -> NoSQL : ( key, Value )

        // SQLite -> 실제 DB 를 안드로이드에 내장
        //SharedPreference -> 환경설정 정보들을 공유
        // 마지막 설정이 저장되어서 재실행시 마지막 설정을 불러오기 ex) 자동로그인


        val btnRed = findViewById<Button>(R.id.btnRed)
        val btnPink = findViewById<Button>(R.id.btnPink)
        val btnBlack = findViewById<Button>(R.id.btnBlack)
        clExam = findViewById(R.id.clExam)
        val btnOther = findViewById<Button>(R.id.btnOther)


        val sharedPreferences =
            getSharedPreferences("sp1", Context.MODE_PRIVATE)

        // MODE_PRIVATE : 생성한 application 내에서 공유 가능
        // MODE_WORLD_READABLE : 다른 application 내에서 읽기 가능
        // MODE_WORLD_WRITEABLE : 다른 application 내에서 읽기,쓰기 가능

        val bgColor:String? = sharedPreferences.getString("bgColor","white")
        clExam.setBackgroundColor(Color.parseColor(bgColor))


        btnRed.setOnClickListener {
            val editor = sharedPreferences.edit()
            val color:String = "#FF0000"
            editor.putString("bgColor",color)
            editor.commit()

            clExam.setBackgroundColor(Color.parseColor(color))
        }

        btnPink.setOnClickListener {
            val editor = sharedPreferences.edit()
            val color:String = "#FF0057"
            editor.putString("bgColor",color)
            editor.commit()

            clExam.setBackgroundColor(Color.parseColor(color))
        }

        btnBlack.setOnClickListener {
            val editor = sharedPreferences.edit()
            val color:String = "#000000"
            editor.putString("bgColor",color)
            editor.commit()

            clExam.setBackgroundColor(Color.parseColor(color))
        }

        btnOther.setOnClickListener {
            val intent = Intent(this@ExamActivity,ColorActivity::class.java)
            startActivity(intent)
        }


    }






}