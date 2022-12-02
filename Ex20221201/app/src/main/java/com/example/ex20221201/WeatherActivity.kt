package com.example.ex20221201

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request.Method
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class WeatherActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)

        val btnWeather = findViewById<Button>(R.id.btnWeather)
        val rvWeather = findViewById<RecyclerView>(R.id.rvWeather)
        val weatherList = ArrayList<WeatherVO>()

        // Volley 활용 네트워크 통신

        // 1. Volley 라이브러리 설정
        // - 라이브러리 추가, 인터넷권한 , http 열기
        // 2. RequestQueue 생성 ( 전역으로 올리는게 FM )
        val requestQueue = Volley.newRequestQueue(this@WeatherActivity)
        // 3. btnWeather 클릭 시 Request 생성

        val adapter=WeatherAdapter(this@WeatherActivity,weatherList)

        rvWeather.adapter = adapter
        rvWeather.layoutManager=LinearLayoutManager(this@WeatherActivity)

        btnWeather.setOnClickListener {

            weatherList.clear()

            val cityList = ArrayList<String>()
                cityList.add("Gwangju")
                cityList.add("Seoul")
                cityList.add("Jeju-do")
                cityList.add("London")
                cityList.add("New York")
                cityList.add("Madrid")
                cityList.add("Beijing")



            val requestList = ArrayList<StringRequest>()
            for( i in 0 until cityList.size){
            val url : String = "https://api.openweathermap.org/data/2.5/weather?q=${cityList.get(i)}&appid=9dbab63c5ba7ea71ab502c849e8a4d4d&units=metric"
                val request = StringRequest(
                    Method.GET,
                    url,
                    {response ->
                        Log.d("날씨$i",response)

                        val result = JSONObject(response)
                        val weathers = result.getJSONArray("weather")

                        val weather = weathers.get(0) as JSONObject

                        val state = weather.getString("main")
                        val main = result.getJSONObject("main")
                        val temp = main.getString("temp")
                        val temp_min = main.getString("temp_min")
                        val temp_max = main.getString("temp_max")
                        val humidity  = main.getString("humidity")

                        val wind = result.getJSONObject("wind")
                        val speed = wind.getString("speed")

                        Log.d("현재날씨$i","상태 : $state , " +
                                "온도 : $temp , 최저온도 : $temp_min , 최저온도 : $temp_max" +
                                ", 습도 : $humidity , 풍속 : $speed" )

                        weatherList.add(WeatherVO(cityList.get(i),state, temp, temp_min, temp_max, humidity, speed))

                        adapter.notifyDataSetChanged()


                    },
                    { error ->
                        Log.d("err",error.toString())
                    }
                )

                requestList.add(request)

            }
            for(i in 0 until requestList.size){
            requestQueue.add(requestList.get(i))
            }





        }


    }
}