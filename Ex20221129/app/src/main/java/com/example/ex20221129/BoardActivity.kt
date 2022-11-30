package com.example.ex20221129

import android.content.DialogInterface
import android.content.DialogInterface.OnClickListener
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog

class BoardActivity : AppCompatActivity() {
    lateinit var btnLogin1: Button
    lateinit var btnWrite: Button
    lateinit var tvContent: TextView
    lateinit var btnBoard : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_board)

        tvContent = findViewById(R.id.tvContent)
        btnWrite = findViewById(R.id.btnWrite)
        btnLogin1 = findViewById(R.id.btnLogin1)
        val lv = findViewById<ListView>(R.id.lv)
        val etBoard = findViewById<EditText>(R.id.etBoard)
        btnBoard = findViewById(R.id.btnBoard)
        btnBoard.isEnabled=false



        btnLogin1.setOnClickListener {
            val intent = Intent(
                this@BoardActivity,
                LoginActivity::class.java
            )

            launcher.launch(intent)
        }


//        lv.setOnItemClickListener(object : AdapterView.OnItemClickListener {
//            // onItemClick 매개변수 -> {parent,view,position,id}
//            override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
//                if (p0 != null) {
//                    var toast = p0.adapter.getItem(p2).toString()
//                    Toast.makeText(applicationContext, toast, Toast.LENGTH_SHORT).show()
//                }
//            }
//        })

        lv.setOnItemClickListener { adapterView, view, i, l ->
            // adapterView -> ListView 대한 정보
            // view -> ListView 가 있는 현재 페이지에 대한 정보
            // i(position) -> 내가 클릭한 item 위치
            // l(:Long -> id) -> 내가 클릭한 item 의 고유한 값

            // i(position)을 통해 내가 클릭한 아이템 확인하기


            Toast.makeText(this,
                adapterView.adapter.getItem(i).toString(),
                Toast.LENGTH_SHORT).show()

        }

        // 1. Container 결정
        // -> ListView 의 위치를 결정

        // 2. Template 결정
        // -> 각 항목(Item)에 적용될 디자인 결정
        //  Layout 패키지에 xml 형태로 생성
        // 최상의 레이아웃 -> TextView
        // 이 때 id는 tbBoard


        // 3. Item 결정
        val data = ArrayList<String>()
        data.add(" 1. 오늘음 점심이 별로였다")
        data.add(" 2. 예진쌤 졸귀")
        data.add(" 3. 나는 곧 한 살 더 먹겠지"  )

        // 4. Adapter 결정
        // Adapter -> 디자인 (항목 뷰, 템플릿) + Item 결합해서
        // Adapter View 에 동적으로 뿌려주는 역할

        // ArrayAdapter 사용
        // 조건) 템플릿이 TextView, 아이템 요소가 String
        // 생성자 요소(4)
        // 1) 페이지 정보
        // 2) 템플릿(항목 뷰)
        // 3) TextView 의 id
        // 4) item (디자인과 연결할 아이템)
        val adapter = ArrayAdapter<String>(this,R.layout.board_list,
        R.id.tvBoard, data)


        // 5. Container 에 Adapter 부착
        lv.adapter = adapter

        // 6. Event 처리
        // btnBoard 클릭 시 etBoard 값 가져와서 val input 에 저장 data 에 input추가

        btnBoard.setOnClickListener {
            val input = etBoard.text.toString()
            data.add(input)

            // adapter 새로고침
            adapter.notifyDataSetChanged()
            etBoard.text = null
        }

        lv.setOnItemClickListener { adapterView, view, i, l ->

            // AlertDialog 옵션 정보를 담는 builder 생성
            val builder = AlertDialog.Builder(this)
            builder
                .setTitle("게시글 삭제")
                .setMessage("정말 삭제하시겠습니까?")
                .setPositiveButton("삭제",
                DialogInterface.OnClickListener{dialogInterFace,id ->
                    data.removeAt(i)
                    adapter.notifyDataSetChanged()
                })
                .setNegativeButton("취소",null)
                .show()

            // builder 를 통해 옵션을 단 이후 마지막 show() 함수 호출



        }







    }

    val launcher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {

        if (it.resultCode == RESULT_OK) {
            btnWrite.isEnabled = true
            btnBoard.isEnabled = true

            tvContent.text = "로그인 성공"
            btnLogin1.text = "로그아웃"
        } else if (it.resultCode == RESULT_CANCELED) {
            tvContent.text = "로그인 실패"
        }

    }


}