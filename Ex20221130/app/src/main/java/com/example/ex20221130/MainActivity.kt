package com.example.ex20221130

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView

class MainActivity : AppCompatActivity() {

    var phoneList = ArrayList<PhoneVO>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 1. Container ( 위치 지정 ) activity_main.xml
        // 2. 디자인 뷰 정하기 ( 한 칸에 들어가는 디자인 ) phone_list.xml

        // 3. ListView 에 들어갈 데이터 만들기 -> phoneVo.kt 자료형 Class 생성

        val p1 = PhoneVO(R.drawable.dw,"김동원","010-1234-4567")
        val p2 = PhoneVO(R.drawable.yj,"강예진","010-8910-1112")
        val p3 = PhoneVO(R.drawable.sm,"채수민","010-1314-1516")
        val p4 = PhoneVO(R.drawable.yp,"선영표","010-1718-1920")
        val p5 = PhoneVO(R.drawable.jy,"조자연","010-2122-2324")
        val lv = findViewById<ListView>(R.id.lv)

        // 보통은 아래와 같이 데이터 추가 ( 추가 변수 데이터 할당 X )
        // phoneList.add(PhoneVO(R.drawable.dw,"김동원","010-1234-4567"))

        phoneList.add(p1)
        phoneList.add(p2)
        phoneList.add(p3)
        phoneList.add(p4)
        phoneList.add(p5)

        // 4. Adapter 생성 ( VO 자료형 Adapter )
        val adapter = PhoneAdapter(applicationContext, // 현재 화면 정보
                                    R.layout.phone_list, // Layout
                                    phoneList) // Data


        // 5. ListView - Adapter 적용

        // 6. Event 추가 ( List 내 이벤트 발생 시 )
        lv.adapter = adapter





    }
}