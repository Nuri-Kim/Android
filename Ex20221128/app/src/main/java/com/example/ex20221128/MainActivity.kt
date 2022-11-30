package com.example.ex20221128

import android.app.SearchManager
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import androidx.core.app.ActivityCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnCall = findViewById<Button>(R.id.btnCall)
        val btnWeb = findViewById<Button>(R.id.btnWeb)
        val btnGoogle = findViewById<Button>(R.id.btnGoogle)
        val btnSearch = findViewById<Button>(R.id.btnSearch)
        val btnSms = findViewById<Button>(R.id.btnSms)
        val btnPhoto = findViewById<Button>(R.id.btnPhoto)

        // 암묵적 intent
        // 안드로이드 내부에 있는 어플리케이션을 실행
        // Crome, Camera, Message, Call

        //Intent 사용 용도
        // 1. 액션, 데이터
        // 2.  Android 4대 구성 요소간의 데이터 주고 받을 때

        btnCall.setOnClickListener {
            //btnCall 을 누르면 전화가 가게 만들기
            // 데이터 : 전화번호
            // URI : key, value


            var uri = Uri.parse(("tel:010-1234-5678"))

            var intent = Intent(Intent.ACTION_CALL,uri)

            // Permission 부여 창 띄우기

            // ActivityCompat
            //checkSelfPermission() -> 지금 현재 권한이 부여되어 있는지
            // ( 현재 페이지 정보, 어떤 권한 인지)
            // 결과값으로 승인이 되었는지 받아오는 역할

            if( ActivityCompat.checkSelfPermission(this,
                    android.Manifest.permission.CALL_PHONE) !=
                    PackageManager.PERMISSION_GRANTED){
                //승인이 안 된 상태라면 알림창을 띄워 권한부여 창 띄우기

                // ActivityCompat -> 확인, 요청 기능 모두 가지고 있음

                ActivityCompat.requestPermissions(this,
                // 모든 권한을 넣기 ( 여러개의 권한이 필요한 경우 )
                arrayOf(android.Manifest.permission.CALL_PHONE),
                    0 //( requestCode -> 내가 구분하기 위한 코드 )
                )
                
                // 권한을 부여한 뒤 이전 페이지로 보내주기
                return@setOnClickListener
            }

            // Intent 실행
            startActivity(intent)

        }

        btnWeb.setOnClickListener {
            // 데이터 -> 구글 주소(http://www.google.co.kr)
            var uri = Uri.parse(("http://www.google.co.kr"))

            var intent = Intent(Intent.ACTION_VIEW,uri)

            startActivity(intent)

        }

        // 구글 맵 열기
        btnGoogle.setOnClickListener {
            // 데이터 : 구글 맵은 get 방식
            var uri = Uri.parse(("https://google.com/maps?q=35.14670147841655,126.92215633785938"))

            var intent = Intent(Intent.ACTION_VIEW,uri)

            startActivity(intent)
        }


        // 해당 키워드로 구글 검색
        btnSearch.setOnClickListener {
            // 1. 검색하는 intent 하나 생성
            var intent = Intent(Intent.ACTION_WEB_SEARCH)

            // 2. 검색하고싶은 키워드를 intent 에 넣어준다
            intent.putExtra(SearchManager.QUERY,"Ateez")

            // 3. Intent 실행
            startActivity(intent)
        }

        // 문자보내기
        // 문자 페이지로 이동 후 미리 적어둔 내용을 꺼내오기
        btnSms.setOnClickListener{

            var intent = Intent(Intent.ACTION_SENDTO,)

            // 필요 데이터-> 문자 내용, 받는 사람

            //문자 내용 데이터
            // "sms_body" 값으로 문자 내용임을 구분
            intent.putExtra("sms_body","안녕하세요")
            intent.data = Uri.parse("smsto:"+Uri.encode("010-1234-5678"))

            startActivity(intent)
        }


        // 사진찍기

        btnPhoto.setOnClickListener {
            // MediaStore : Emulator 에서 동작하는 카메라, 저장소 제공
            var intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

            startActivity(intent)
        }



    }
}