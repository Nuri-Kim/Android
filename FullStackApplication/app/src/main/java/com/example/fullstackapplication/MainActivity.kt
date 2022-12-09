package com.example.fullstackapplication

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.Toast
import com.example.fullstackapplication.auth.IntroActivity
import com.example.fullstackapplication.fragment.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().replace(
            R.id.flMain,
            Fragment1()
        ).commit()

        auth =Firebase.auth

        val bnvMain = findViewById<BottomNavigationView>(R.id.bnvMain)
        val bnvMain2 = findViewById<BottomNavigationView>(R.id.bnvMain2)
        val flMain = findViewById<FrameLayout>(R.id.flMain)
        val imgLogout = findViewById<ImageView>(R.id.imgLogout)


        bnvMain2.setOnItemSelectedListener{
            item->
            when(item.itemId){
                R.id.tab6 -> {
                    supportFragmentManager.beginTransaction().replace(
                        R.id.flMain,
                        ChatFragment()
                    ).commit()
                }
            }
            true
        }


        bnvMain.setOnItemSelectedListener {
            itme->
            // item -> 내가 클릭한 item 정보
            when(itme.itemId){
                R.id.tab1 -> {
                    Toast.makeText(this,"Home Tab", Toast.LENGTH_SHORT).show()

                    // beginTransaction() : Fragment 추가/변경/삭제
                    // replace 매개변수
                    // 1) Fragment 가 들어가는 위치
                    // 2) 대체할 Fragment 객체
                    // 마지막에 commit
                    supportFragmentManager.beginTransaction().replace(
                        R.id.flMain,
                        Fragment1()
                    ).commit()
                }
                R.id.tab2 -> {
                    supportFragmentManager.beginTransaction().replace(
                        R.id.flMain,
                        Fragment2()
                    ).commit()
                }
                R.id.tab3 -> {
                    supportFragmentManager.beginTransaction().replace(
                        R.id.flMain,
                        Fragment3()
                    ).commit()
                }
                R.id.tab4 -> {
                    supportFragmentManager.beginTransaction().replace(
                        R.id.flMain,
                        Fragment4()
                    ).commit()
                }
                R.id.tab5 -> {
                    supportFragmentManager.beginTransaction().replace(
                        R.id.flMain,
                        Fragment5()
                    ).commit()
                }

            }

            true
        }


        imgLogout.setOnClickListener {
            auth.signOut()
            val intent = Intent(this,IntroActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)

            //finish()
        }

        // auth 에 담겨있는 기능들
        // createUserWithEmailAndPassword -> 회원가입 (email,pw 사용)
        // signInWithEmailAndPassword -> 로그인 (email,pw 사용)
        // signInAnonymously -> 익명로그인 (매개변수 X)
        // signOut -> 로그아웃 (페이지 이동 기능 X)







    }
}