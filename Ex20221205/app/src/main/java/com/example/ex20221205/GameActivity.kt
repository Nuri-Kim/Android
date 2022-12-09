package com.example.ex20221205

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import kotlin.random.Random

class GameActivity : AppCompatActivity() {
    var times : Int = 0
    lateinit var tvTimes:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        val btnGame = findViewById<Button>(R.id.btnGame)
        tvTimes = findViewById<TextView>(R.id.tvTimes)
        val numbers = ArrayList<Int>()

        var cnt = 1 //현재 눌러야 되는 숫자



        for(i in 1.. 25){
            numbers.add(i)
        }

        for(i in 0 until 100){
            val rdNum1 = Random.nextInt(25)
            val rdNum2 = Random.nextInt(25)

            val temp = numbers.get(rdNum1)
            numbers[rdNum1] = numbers[rdNum2]
            numbers[rdNum2]=temp

        }

        //Log.d("랜덤",numbers.toString())


        val btns = ArrayList<Button>()

        for (i in 2 .. 26){

            val resId = resources.getIdentifier("button$i","id",packageName)
            val btn = findViewById<Button>(resId)
            btns.add(btn)
            btn.visibility = View.INVISIBLE
        }

        btnGame.setOnClickListener {
            cnt = 1
            val numbersList = numbers.shuffled()
            val thread = TimeThread()


            for (i in 0 until btns.size){
                val btn = btns.get(i)
                btn.text = numbersList.get(i).toString()
                btn.visibility = View.VISIBLE

                btn.setOnClickListener {
                    if(btn.text == cnt.toString()){
                        btn.visibility = View.INVISIBLE
                        cnt++
                    }
                }
            }
            thread.start()
        }
    }
    val handler = object : Handler(Looper.getMainLooper()){
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            val time = msg.arg1


            tvTimes.text = "${time}sec"

        }
    }


    inner class TimeThread() : Thread(){
        override fun run() {
            super.run()


            val message = Message()
            message.arg1 = times

            times++

            handler.sendMessage(message)

            Thread.sleep(1000)




        }
    }

    fun btnSet(btn : Button, value : Int  ){
        btn.setText(value.toString())

    }



}