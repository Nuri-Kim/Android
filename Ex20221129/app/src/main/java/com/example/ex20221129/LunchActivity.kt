package com.example.ex20221129

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView

class LunchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lunch)


        // Adapter View 만드는 6단계

        // 1) Container 결정 ( 위치결정 )
        val lvLunch = findViewById<ListView>(R.id.lvLunch)
        val etAdd = findViewById<EditText>(R.id.etAdd)
        val btnAdd = findViewById<Button>(R.id.btnAdd)
        val btnResult= findViewById<Button>(R.id.btnResult)
        val tvLunch = findViewById<TextView>(R.id.tvLunch)

        // 2) Template 결정 ( 아이템 디자인 )
        // lunch_list.xml

        // 3) Item 결정 ( 만약, 자료형이 여러개라면 VO Class 생성)
        var data = ArrayList<String>()
        data.add("보울레시피")
        data.add("샐러바웃")
        data.add("서브웨이")
        data.add("한식경")
        data.add("피자")
        data.add("돈까스")
        data.add("베트남")
        data.add("스테이크")
        data.add("일식")
        data.add("버거킹")
        data.add("부리또")

       // 4) Adapter 결정 ( 만약, TextView, ArrayList<String>
       //                  사용한다면 ArrayAdapter 사용가능)
        // 1. 페이지 정보 2. 항목뷰 디자인 3.TextView Id 4.data
        val adapter = ArrayAdapter<String>(this,
            R.layout.lunch_list,R.id.tvMenu,data)

        // 5) Container 에 Adapter 부착
        lvLunch.adapter = adapter

        // 6) Event 처리
        btnAdd.setOnClickListener {
            data.add(etAdd.text.toString())
            adapter.notifyDataSetChanged()

            etAdd.text = null
        }


        btnResult.setOnClickListener {
            tvLunch.text = data.random()
        }




    }
}