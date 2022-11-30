package com.example.ex20221129

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts

lateinit var tvResult : TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvResult = findViewById(R.id.tvResult)
        val btnNext = findViewById<Button>(R.id.btnNext)

        btnNext.setOnClickListener {
            val intent = Intent(this@MainActivity,
                SubActivity::class.java)

            // 가운데 줄이 있는 코드는 다음 업데이트에 삭제될 수 있다는 표기
            //startActivityForResult(intent, 1004)
            // RequestCode -> 결과 값을 보내줘야 하는 Activity 구분을 위해

            // 실행 시 Launch() 코드로 실행 전달 인자로 생성한 intent 넣기
            launcher.launch(intent)


        }



    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(1004, RESULT_OK, data)
//
//        // 1) requestCode -> 1004 내가 보냈던 요청코드
//        // 2) resultCode -> RESULT_OD -> SubActivity 에서 전송한 코드
//        // 3) Intent? data -> Sub 에서 전송한 str 이 붙어있는 Intent
//
//        if(requestCode==1004){
//            if(resultCode == RESULT_OK){
//                val str = data?.getStringExtra("content")
//                tvResult.text = str
//            }
//        }
//
//    }

    // CallBack 함수
    // 1) 다른 함수의 인자로 사용되는 함수
    // 2) 어떤 이벤트에 의해 호출되는 함수 ex) 버튼 클릭 시 Intent 실행시키며 호출

    // ActivityResultLauncher 사용
    // -> Activity 에서 데이터를 받아오기 위해 사용
    // Fragment (부분화면) 에서도 데이터 주고받는 경우 사용 가능

    // 기존 ActivityForResult 는 메모리가 너무 작음 -> Process, Activity 소멸 가능성+
    // Launcher 메모리 부족으로 소멸 후 재생성해도 결과값 가져온다

    // 매개변수 -> Contract  자료형, CallBack 메서드
    val launcher = registerForActivityResult(ActivityResultContracts.
    StartActivityForResult()){
        // 실제로 ActivityResultContracts 를 타고 들어가면
        // 1) createIntent -> StartActivityForResult 를 대체
        // 2) parseResult -> onActivityResult 대체 (resultCode, Intent 받아옴)
        // 런처를 사용하게 되면 요청을 보낸 Activity 를 구분할 필요가 없다
        // requestCode 사용하지 않아도 된다
        Log.d("data",it.data.toString())
        Log.d("data",it.resultCode.toString())

        // ResultCode 가 RESULT_OK 인지 확인
        if(it.resultCode == RESULT_OK){
            tvResult.text = it.data?.getStringExtra("content")
        }



        }




}