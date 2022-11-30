package com.example.ex221130

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Adapter View

        // ListView
        // -> 안쪽에서 메모리 리소스 많이 잡아먹는 함수가 많이 사용된다

        val lvPoke = findViewById<ListView>(R.id.lvPoke)

        val pokeList = ArrayList<PokeVO>()

        pokeList.add(PokeVO(R.drawable.p1,"피카츄","전기","1"))
        pokeList.add(PokeVO(R.drawable.p2,"꼬부기","물","1"))
        pokeList.add(PokeVO(R.drawable.p3,"파이리","불","1"))
        pokeList.add(PokeVO(R.drawable.p4,"이상해씨","풀","1"))
        pokeList.add(PokeVO(R.drawable.p5,"버터풀","벌레","1"))
        pokeList.add(PokeVO(R.drawable.p6,"구구","비행","1"))


        val adapter = PokeAdapter(this,pokeList)

        lvPoke.adapter =adapter



    }
}