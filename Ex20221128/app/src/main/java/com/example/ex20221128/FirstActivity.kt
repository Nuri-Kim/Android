package com.example.ex20221128

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import android.widget.Button
import android.widget.ListView

class FirstActivity : AppCompatActivity() {

    var color : String = "white"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)

//        val btnNext = findViewById<Button>(R.id.btnNext)
//
//        btnNext.setOnClickListener {
//            // btnNext 클릭 시 SecondActivity 로 이동
//
//            // Activity 로 이동하는 Intent
//            // (시작 Activity , 도착 Activity)
//            // ( this , java Class 를 넣어줘야 하나 현재는 K Class)
//            // 액티비티명::class.java 로 변환 가능
//            var intent = Intent(this,
//                SecondActivity::class.java)
//
//            startActivity(intent)
//        }

        val btnNext = findViewById<Button>(R.id.btnNext)

        val lv = findViewById<ListView>(R.id.lv)


        // inner Class (익명클래스 anonymous Class)

        lv.setOnItemClickListener(object : OnItemClickListener{
            override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

                if(p0 != null){
                    color = p0.adapter.getItem(p2).toString()
                }

                Log.d("보내준값",color)


            }
        })


        btnNext.setOnClickListener {
            // btnNext 클릭 시 SecondActivity 로 이동 ( 컬러 코드를 가지고 넘어감 )

            var intent = Intent(
                this@FirstActivity,
                SecondActivity::class.java
            )

            intent.putExtra("color",color)
            
            // 단방향 호출
            startActivity(intent)

            //finish()

        }

    }


//    override fun onStart() {
//        super.onStart()
//        Log.d("생명주기", "onStart 입니다")
//    }
//
//    override fun onStop() {
//        super.onStop()
//        Log.d("생명주기", "onStop 입니다")
//    }
//
//    override fun onResume() {
//        super.onResume()
//        Log.d("생명주기", "onResume 입니다")
//    }
//
//    override fun onRestart() {
//        super.onRestart()
//        Log.d("생명주기", "onRestart 입니다")
//    }
//
//    override fun onPause() {
//        super.onPause()
//        Log.d("생명주기", "onPause 입니다")
//    }

}
