package com.example.ex20221124

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView

class ImageActivity : AppCompatActivity() {

    // Kotlin 내 배열 선언
    val imgArray = intArrayOf(R.drawable.blue,R.drawable.yellow,R.drawable.pink,R.drawable.black,R.drawable.red)
    var index = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image)

        // View의 id 값 다 찾아오기
        val img = findViewById<ImageView>(R.id.img)
        val btnPre = findViewById<Button>(R.id.btnPre)
        val btnNext = findViewById<Button>(R.id.btnNext)


        // Pre 버튼을 눌렀을 때

        btnPre.setOnClickListener{
            // index -1
            // 해당 index 이미지 출력
            // 0보다 작으면 마지막 인덱스로 돌아가게

            img.setImageResource(imgArray[index])
            if(index>0){
                index--
            }else{
                index=imgArray.size-1
            }






        }

        // Next 버튼을 눌렀을 때
        btnNext.setOnClickListener{
            // index +1
            // 해당 index 이미지 출력
            // index 가 size - 1 크면 처음 인덱스로 돌아가게

            img.setImageResource(imgArray[index])
            if(index>=imgArray.size-1){
                index=0
            }else{
                index++
            }

        }



    }
}