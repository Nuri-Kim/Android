package com.example.ex20221124

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

// : -> Kotlin 내 상속 기호

class ConstraintActivity : AppCompatActivity() {

    // 전역변수(뷰)로 만들기
    // 뷰는 선언만 할 수 없음 초기화가 이루어져야 함
    // lateinit var -> 키워드로 나중에 초기화 할 수 있음
    lateinit var etNum1 : EditText
    lateinit var etNum2 : EditText
    lateinit var tvResult : TextView

    // OnCreate() -> Activity 실행 시 최초로 한 번만 실행되는 메서드
    // Activity 생명주기 부분에서 다룰 내용
    // savedInstanceState -> 가로, 세로 출력 방향 변경 시 데이터 저장
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_constraint) // xml 파일과 연결해주는 코드

        // 1. xml 내 View 에 id 값 지정

        // 2. id 값을 이용하여 View 를 가져오기 ( findViewById< view 타입 >( R.id.아이디 이름) )
        // R -> Resource 폴더
        // id 값을 문자열로 저장했으나 불러올 땐 주소값으로 저장되기 때문에
        // R 폴더에서 Int (16진수의 상수) 형태로 호출

        // 전역변수로 선언 시 자료형 생략 가능 ( 이전에 선언하기 때문 )
        etNum1 = findViewById(R.id.etNum1)
        etNum2 = findViewById(R.id.etNum2)
        tvResult = findViewById(R.id.tvResult)


        val btnMinus = findViewById<Button>(R.id.btnMinus)
        val btnMul = findViewById<Button>(R.id.btnMul)
        val btnDiv = findViewById<Button>(R.id.btnDiv)


        // setContentView 위로 find 할 수 없다 연결 전 찾기 불가능

        //tvResult.setTextColor(Color.BLUE)
        //tvResult.setTextColor(Color.parseColor("#ff9999"))

        // textSize 에는 Float 자료형이 들어가야 한다 (f 추가하여 형변환 -> 처음에는 더블)
        //tvResult.textSize = 40.0f
        
        //tvResult.text = "안녕하세요"
        // charSequence 인터페이스 -> String CharSequence 인터페이스를 상속받는 중

        // - 더하기 버튼 클릭 시 " 더하기 버튼이 눌렸습니다 " Toast 띄우기
        // 이벤트를 주는 방법
        // 1) 이벤트 메소드 설계 후 뷰에 연결하기
        // 2) innerClass ( Listener OnClick 구현 )

        // 1. EditText 에 적혀있는 값 가져오기
        // getText --> Editable 타입 --> String 형변환 필요
        // 2. 연산 결과를 tvResult 에 set

        btnMinus.setOnClickListener {
            // {} 안에 기능 구현만 작성하면 됨
            // "마이너스 버튼이 눌렸습니다" 토스트 창 띄우기
            Toast.makeText(this,"마이너스 버튼이 눌렸습니다",Toast.LENGTH_SHORT).show()

            var num1 = etNum1.text.toString().toInt()
            var num2 = etNum2.text.toString().toInt()

            tvResult.text="연산결과 : ${num1-num2}"
        }

        // 3) Interface 를 상속받아 OnClick 구현

        btnMul.setOnClickListener {
            var num1 = etNum1.text.toString().toInt()
            var num2 = etNum2.text.toString().toInt()

            var result = (num1*num2)
            tvResult.text="연산결과 : $result"
        }

        btnDiv.setOnClickListener {
            var num1 = etNum1.text.toString().toInt()
            var num2 = etNum2.text.toString().toInt()

            var result = (num1/num2)
            tvResult.text="연산결과 : $result"
        }



    } //onCreate 바깥쪽
    // 이벤트 리스너는 무조건 View 매개변수를 가지고 있어야 한다.
    fun myClick(view:View){
        // Toast 띄우기
        // 1) this -> 지금 이 페이지 ( ConstraintActivity.this 로 대체 가능) -> Toast 띄울 화면
        // 2) 문구 (무조건 String, Int 는 아이디 값만 허용)
        // 3) 지속 시간( Toast.LENGTH_SHORT(3초)/LONG(5초) 만 가능
        Toast.makeText(this,"더하기 버튼이 눌렸습니다",Toast.LENGTH_SHORT).show()

        var num1 = etNum1.text.toString().toInt()
        var num2 = etNum2.text.toString().toInt()

        // Emulator 처음 실행 시  EditText 에 아무 값도 없음
        // "" 를 .toInt 실행 시  NumberFormatException 발생
        // 버튼을 눌렀을 때 적혀있는 값을 가지고 와야 함

        var result = (num1+num2)
        tvResult.text="연산결과 : $result"

    }
    
}