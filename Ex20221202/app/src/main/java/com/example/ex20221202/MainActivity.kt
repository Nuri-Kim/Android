package com.example.ex20221202

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.FrameLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().replace(
            R.id.fl,
            Fragment1()
        ).commit()

        // View 들의 ID 값 찾아오기

        val btnLog = findViewById<Button>(R.id.btnLog)
        val bnv = findViewById<BottomNavigationView>(R.id.bnv)
        val fl = findViewById<FrameLayout>(R.id.fl)

        // Fragment 구현
        // bnv 에서 선택한 메뉴에 따라 fl 에 표시할 Fragment 를 갈아 끼운다

        bnv.setOnItemSelectedListener {
            // item -> 내가 선택한 메뉴의 정보

            // 내가 선택한 item 의 id 값을 가져오기
            Log.d("아이디가져오기",it.itemId.toString())

            when(it.itemId){
                R.id.tab1 -> {
                    Toast.makeText(this,"Home Tab",Toast.LENGTH_SHORT).show()

                    // beginTransaction() : Fragment 추가/변경/삭제
                    // replace 매개변수
                    // 1) Fragment 가 들어가는 위치
                    // 2) 대체할 Fragment 객체
                    // 마지막에 commit
                    supportFragmentManager.beginTransaction().replace(
                        R.id.fl,
                        Fragment1()
                    ).commit()
                }


                R.id.tab2 -> {
                    Toast.makeText(this,"Board Tab",Toast.LENGTH_SHORT).show()
                    supportFragmentManager.beginTransaction().replace(
                        R.id.fl,
                        Fragment2()
                    ).commit()
                }
                R.id.tab3 -> {
                    Toast.makeText(this,"Write Tab",Toast.LENGTH_SHORT).show()
                    supportFragmentManager.beginTransaction().replace(
                        R.id.fl,
                        Fragment3()
                    ).commit()
                }
                R.id.tab4 -> {
                    Toast.makeText(this,"Setting Tab",Toast.LENGTH_SHORT).show()
                    supportFragmentManager.beginTransaction().replace(
                        R.id.fl,
                        Fragment4()
                    ).commit()
                }
            }

            // false -> 이벤트가 끝나지 않음 클릭해도 효과 변경없음
            true
        }




    }
}