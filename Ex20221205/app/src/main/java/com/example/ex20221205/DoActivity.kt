package com.example.ex20221205

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import kotlin.collections.ArrayList
import kotlin.random.Random


class DoActivity : AppCompatActivity() {
    var isPlaying : Boolean = true
    var score : Int = 0 // 점수 저장하는 변수
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_do)

        val tvScore = findViewById<TextView>(R.id.tvScore)
        val btnStart = findViewById<Button>(R.id.btnStart)
        val tvTime = findViewById<TextView>(R.id.tvTime)

        val imgViews = ArrayList<ImageView>()

        for(i in 1 .. 9){
            val resId = resources.getIdentifier("imageView$i","id",packageName)

            val imgView = findViewById<ImageView>(resId)
            imgViews.add(imgView)
            imgView.visibility = View.INVISIBLE
            // 각 이미지뷰의 tag 는 최초 0 이다
            // 0 = Off
            imgView.tag = 0
        }

        btnStart.setOnClickListener {
            val threadTime = TimeThread(tvTime)
            threadTime.start()
            for(i in 0 until imgViews.size){
                val imgView =imgViews.get(i)

                imgView.visibility = View.VISIBLE

                val thread = DoThread(imgView)
                thread.start()

                imgView.setOnClickListener {
                    if(imgView.tag == 1){
                        score++

                    }else{
                        if(score>0){
                        score--
                        }
                    }
                    tvScore.setText("현재 점수 : $score")

                }

            }
        }

    }

    val handlerTime = object : Handler(Looper.getMainLooper()){
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            val time = msg.arg1
            val tv = msg.obj as TextView
            tv.setText(time.toString())
        }
    }

    inner class TimeThread(val tv:TextView):Thread(){
        override fun run() {
            super.run()

            for(i in 30 downTo 0){
                val message = Message()
                message.arg1 = i
                message.obj = tv

                handlerTime.sendMessage(message)
                Thread.sleep(1000)
            }
            isPlaying = false
            val intent = Intent(this@DoActivity,ResultActivity::class.java)
            startActivity(intent)



        }
    }




    val handler = object :Handler(Looper.getMainLooper()){
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            val imgView = msg.obj as ImageView // 적용될 이미지뷰
            val img = msg.arg1 // on / off 인지 구분
            val tag = msg.arg2 // 1 -> on , 1-> off

            imgView.setImageResource(img)

            imgView.tag = tag
        }
    }

    inner class DoThread(val imgView:ImageView):Thread(){
        override fun run() {
            super.run()

           while(isPlaying){

               var level = score * 20
               if(score >= 40){
                   level = 600
               }
               val onTime = Random.nextInt(5*(1000-level))

               Thread.sleep(onTime.toLong())

               var onMessage = Message()

               onMessage.arg1 = R.drawable.on
               onMessage.arg2 = 1
               onMessage.obj = imgView

               handler.sendMessage(onMessage)


               val offTime = Random.nextInt(3*(1000-level))

               Thread.sleep(offTime.toLong())

               val offMessage = Message()

               offMessage.arg1 = R.drawable.off
               offMessage.arg2 = 0
               offMessage.obj = imgView

               handler.sendMessage(offMessage)
           }

        }



    }


}