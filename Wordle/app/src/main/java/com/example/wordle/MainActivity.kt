package com.example.wordle

import android.content.Context
import android.inputmethodservice.InputMethodService
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val answer ="apple"

        val rvGame = findViewById<RecyclerView>(R.id.rvGame)
        val tvAnswer = findViewById<TextView>(R.id.tvAnswer)
        val gameList = ArrayList<GameVO>()



        gameList.add(GameVO("","","","",""))
        gameList.add(GameVO("","","","",""))
        gameList.add(GameVO("","","","",""))
        gameList.add(GameVO("","","","",""))
        gameList.add(GameVO("","","","",""))



        val adapter = GameAdapter(this,gameList,answer)

        rvGame.adapter=adapter
        rvGame.layoutManager=LinearLayoutManager(this)




    }
}