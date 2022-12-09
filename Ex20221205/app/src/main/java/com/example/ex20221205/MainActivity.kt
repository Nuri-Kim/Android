package com.example.ex20221205

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import android.widget.TextView
import java.util.Random

lateinit var tvTimer : TextView
lateinit var tvTimer2 : TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvTimer = findViewById(R.id.tvTimer)
        tvTimer2 = findViewById(R.id.tvTimer2)

        val thread = TimerThread(tvTimer)
        thread.start() // thread class 안에 run 을 실행

        val thread2 = TimerThread(tvTimer2)
        thread2.start()

    }
    // Main Thread Queue 작업 영역에 작업 추가하는 Handler 생성

    val handler = object : Handler(Looper.getMainLooper()){
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            val time = msg.arg1
            val tv = msg.obj as TextView

            // Main UI 를 수정하는 작업은 main 작업 Queue 에 Message 로 전달하는 역할
            // 직접적인 수정이 아님

            tv.text = time.toString()

        }
    }


    // 시간 관련 ThreadClass
    inner class TimerThread(val tv : TextView) : Thread(){

        // Thread 클래스 내 run() 메소드
        // -> Thread 를 동작시키면 실행되는 메소드

        // ctrl + O -> OverRide 단축기
        override fun run() {
            super.run()



            // 10 -> 0 ( 1초마다 1씩 감소 )
            // sleep -> milli sec 단위로 안쪽 코드를 지연시킴

            for(i in 10 downTo  0){
                Log.d("타이머",i.toString())

                // Handelr 에게 정보를 전달해주는 객체
                val message = Message()
                // member(변수) 3개가 존재
                // arg1, arg2, obj
                    message.arg1 = i
                    message.obj = tv


                handler.sendMessage(message)

                val rdValue = Random().nextInt(1000)

                Thread.sleep(rdValue.toLong())
            }
        }
    }

}