package com.example.ex20221201

import android.annotation.SuppressLint
import android.content.Intent
import android.icu.lang.UCharacter.VerticalOrientation
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class MovieActivity : AppCompatActivity() {

    var queue : RequestQueue? = null // 요청을 가지고 서버로 이동하는 객체
    lateinit var request: StringRequest // 요청/응답이 들어가는 객체
    var movies = ArrayList<MovieVO>()

    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)

        // Volley 를 통한 네트워크 통신 4단계
        // 1. Volley 설정
        // - Volley 라이브러리 추가 ( 버전 확인하기 )
        // - Manifest 에 Permission 추가 ( Internet )
        // 2. RequestQueue 생성
        // 3. Request 생성
        // 4. RequestQueue 에 Request 추가


        val rc = findViewById<RecyclerView>(R.id.rc)
        val btnMovie = findViewById<Button>(R.id.btnMovie)
        val etInput = findViewById<EditText>(R.id.etInput)

        val adapter = MovieAdapter(this, movies)
        rc.adapter=adapter

        rc.layoutManager=LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        // btnMovie 클릭 시, 영화정보 response Log 로 확인하기


        // 버튼을 클릭하거나, 에뮬레이터를 작동, OnCreate 가 실행될 때마다
        // Request 가 들어갈 장소를 생성하는 중
        if(queue == null){
        queue = Volley.newRequestQueue(this@MovieActivity)
        }

        btnMovie.setOnClickListener {

        movies.clear()
        val date = etInput.text.toString()

        val url ="https://kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json?key=f5eef3421c602c6cb7ea224104795888&targetDt=$date"


            request = StringRequest(
                Request.Method.GET,
                url,

                {response ->
                    val json1 = JSONObject(response)
                    val json2 = json1.getJSONObject("boxOfficeResult")
                    val json3 = json2.getJSONArray("dailyBoxOfficeList")

                     Log.d("test json1",json1.toString())
                     Log.d("test json2",json2.toString())
                     Log.d("test json3",json3.toString())

                    val movie = json3.getJSONObject(0)
                    val rank = movie.getString("rank")
                    val name = movie.getString("movieNm")

                   Log.d("test rank",rank)
                   Log.d("test name",name)

                    // JsonArray 에 순차적으로 접근해서 정보 받아오기

                    for( i in 0 until json3.length()){
                        val movie = json3.getJSONObject(i)
                        val rank = movie.getString("rank")
                        val rankOldAndNew =  movie.getString("rankOldAndNew")
                        val movieNm = movie.getString("movieNm")
                        Log.d("영화",movieNm)
                        val openDt = movie.getString("openDt")
                        val audiCnt = movie.getString("audiCnt")
                        val audiAcc = movie.getString("audiAcc")

                        movies.add(MovieVO(rank,rankOldAndNew,movieNm,audiAcc,audiCnt,openDt))


                    }

                adapter.notifyDataSetChanged()

                },{error->
                    Log.d("test Res",error.toString())
                }

                )

                queue?.add(request)




        }




    }
}