package com.example.ex221130

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ColorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_color)

        val rvColor = findViewById<RecyclerView>(R.id.rvColor)

        val colorList = ArrayList<ColorVO>()
        var color:String


        for(i in 16..255 step 4){
            var red:String =Integer.toHexString(i)

            for(j in 16..255 step 4){

                var green:String =Integer.toHexString(j)
                if(red.length == 1){ red = "0"+red}
                if(green.length == 1){ green = "0"+green}
                color = "#$red$green$green"
                colorList.add(ColorVO(color))

            }
        }





        val adapter = ColorAdapter(this,colorList)

        rvColor.adapter = adapter

        rvColor.layoutManager = GridLayoutManager(this,60)






    }
}