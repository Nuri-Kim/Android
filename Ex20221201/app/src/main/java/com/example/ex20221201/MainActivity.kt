package com.example.ex20221201

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class MainActivity : AppCompatActivity() {

    lateinit var queue : RequestQueue
    lateinit var request : StringRequest // 받아오는 응답이 문자열

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // Volley : 서버 통신을 위한 라이브러리
        // Request : 내가 하고 싶은 요청이 담기는 곳
        // RequestQueue : Request 에 담긴 요청을 가지고 서버로 이동하는 객체

        val btnReq = findViewById<Button>(R.id.btnReq)
        val etUrl = findViewById<EditText>(R.id.etUrl)
        val tvResult = findViewById<TextView>(R.id.tvResult)

        //Queue 초기화 진행
        queue = Volley.newRequestQueue(this@MainActivity)


        // btnReq 클릭 시 etUrl 에 적힌 값을 가져와서 request 를 생성

        btnReq.setOnClickListener {
            val url = etUrl.text.toString()

            // 실제 요청을 할 수 있는 객체 -> Request
            // String Request 의 매개변수 4가지
            // 1) Data 전송 방식( GET/POST )
            // 2) 요청할 서버 주소
            // 3) 응답 성공했을 경우 실행시킬 코드
            // 4) 응답 실패시 실행시킬 코드

            request = StringRequest(
                Request.Method.GET,
                url,
                // 응답 성공 시
                {response ->
                    Log.d("test Response",response.toString())
                    tvResult.text = response
                    Toast.makeText(this,"응답 성공",Toast.LENGTH_SHORT).show()
                },
                { error->
                    Log.d("test Error",error.toString())
                    Toast.makeText(this,"error 발생",Toast.LENGTH_SHORT).show()
                }

            )
            // RequestQueue 가 받아온 응답은 response 랑 error 매개 변수를
            // 통해 확인이 가능합니다
            request.setShouldCache(false)


            queue.add(request)



        }



    }
}