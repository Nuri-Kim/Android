package com.example.ex221130

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.HorizontalScrollView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

    // RecycleView
        // ListView 의 개선판 -> ViewHolder 강제
        // LayOutManager 유연하다

        // AdapterView 6단계
        // 1) Container 결정
        val rvPoke = findViewById<RecyclerView>(R.id.rvPoke)

        // 2)

        val pokeList = ArrayList<PokeVO>()
        pokeList.add(PokeVO(R.drawable.p1,"피카츄","전기","1"))
        pokeList.add(PokeVO(R.drawable.p2,"꼬부기","물","1"))
        pokeList.add(PokeVO(R.drawable.p3,"파이리","불","1"))
        pokeList.add(PokeVO(R.drawable.p4,"이상해씨","풀","1"))
        pokeList.add(PokeVO(R.drawable.p5,"버터풀","벌레","1"))
        pokeList.add(PokeVO(R.drawable.p6,"구구","비행","1"))
        pokeList.add(PokeVO(R.drawable.p1,"피카츄","전기","1"))
        pokeList.add(PokeVO(R.drawable.p2,"꼬부기","물","1"))
        pokeList.add(PokeVO(R.drawable.p3,"파이리","불","1"))
        pokeList.add(PokeVO(R.drawable.p4,"이상해씨","풀","1"))
        pokeList.add(PokeVO(R.drawable.p5,"버터풀","벌레","1"))
        pokeList.add(PokeVO(R.drawable.p6,"구구","비행","1"))


        val adapter = PokeAdapter2(this,pokeList)

        rvPoke.adapter = adapter
//        rvPoke.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)

        // Grid 형태로 쌓기
        rvPoke.layoutManager = GridLayoutManager(this,2)



    }
}