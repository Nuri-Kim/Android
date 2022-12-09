package com.example.fullstackapplication.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.fullstackapplication.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class JoinActivity : AppCompatActivity() {

    // FireBase Auth 선언하기
    lateinit var auth:FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join)

        val etJoinEmail = findViewById<EditText>(R.id.etJoinEmail)
        val etJoinPw = findViewById<EditText>(R.id.etJoinPw)
        val etJoinCheck = findViewById<EditText>(R.id.etJoinCheck)
        val btnJoinJoin = findViewById<Button>(R.id.btnJoinJoin)

        // auth 초기화
        auth = Firebase.auth

        // Firebase.auth -> 로그인, 회원가입, 인증 시스템에 대한 모든 기능을 담고 이씀


       btnJoinJoin.setOnClickListener {
           var isJoin = true // 조건을 만족했는지 확인인
           val email = etJoinEmail.text.toString()
           val pw = etJoinPw.text.toString()
           val checkPw = etJoinCheck.text.toString()
//            Toast.makeText(this@JoinActivity,
//                "Email : ${etJoinEmail.text} " +
//                        "/ Pw : ${etJoinPw.text}",
//                Toast.LENGTH_SHORT).show()

            // create 가 보내고 있는 전달 인자 (email, pw) 는
            // 실제로 회원가입 정보를 fireBase 로 전달
            // Listener 의 경우 보낸 후 결과(T/F) 구분

           // EditText 내용이 모두 입력되어 있는지
           if(email.isEmpty()){
               isJoin = false
               Toast.makeText(this,
                   "이메일은 필수 입력사항입니다",Toast.LENGTH_SHORT).show()
           }

           if(pw.isEmpty()){
               isJoin = false
               Toast.makeText(this,
                   "비밀번호는 필수 입력사항입니다",Toast.LENGTH_SHORT).show()
           }

           if(checkPw.isEmpty()){
               isJoin = false
               Toast.makeText(this,
                   "비밀번호를 확인하세요",Toast.LENGTH_SHORT).show()
           }

           // 비밀번호, 비밀번호 확인이 일치하는지
           if(pw != checkPw){
               isJoin = false
               Toast.makeText(this,
                   "비밀번호가 일치하지 않습니다",Toast.LENGTH_SHORT).show()
           }

           // 비밀번호가 8자리 이상인지
           if(pw.length < 8){
               isJoin = false
               Toast.makeText(this,
                   "비밀번호는 8자리 이상으로 입력해주세요",Toast.LENGTH_SHORT).show()
           }

           if (isJoin){
               auth.createUserWithEmailAndPassword(email, pw)
                   .addOnCompleteListener(this) { task ->
                       // task -> 보낸 후 결과 ( T of F )
                       if (task.isSuccessful) {
                           // 전달 성공 시 실행시킬 코드
                           Toast.makeText(this,"회원가입 성공!",Toast.LENGTH_SHORT).show()
                           val intent = Intent(this@JoinActivity,LoginActivity::class.java)
                           startActivity(intent)
                           finish()
                       } else {
                           // 전달 실패 시 실행시킬 코드
                           Toast.makeText(this,"회원가입 실패",Toast.LENGTH_SHORT).show()

                       }
                   }

           }

        }


    }



}