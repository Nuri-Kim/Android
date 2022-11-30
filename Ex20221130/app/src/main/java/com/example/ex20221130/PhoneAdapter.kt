package com.example.ex20221130

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class PhoneAdapter(val context: Context,
                   val layOut:Int,
                   val data : ArrayList<PhoneVO> ) : BaseAdapter() {

    // Activity 의 힘을 빌려 Inflate 를 할 수 있는 inflater 가져오기

    var inflater = context.
    getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater?

    //getSystemService
    // -> 하드웨어(스마트폰) 에 담겨있는 센서, Inflate 를 추출 할 수 있는 메서드
    // -> Any 타입으로 반환되기 때문에 각 기능에 맞춰 형변환 필요



    // 프로퍼티 : 필드
    // 멤버 : 메서드
    override fun getCount(): Int {
        // ListView 의 항목의 개수를 돌려주는 기능
        return data.size // 데이터( ArrayList ) 의 개수만큼
    }

    override fun getItem(p0: Int): Any {
        // p0 -> position 그 위치에 있는 data 반환
        return data[p0]
    }

    override fun getItemId(p0: Int): Long {
        // p0 번째 id 값을 반환
        return p0.toLong()
    }

    // 중요한 기능 -> 데이터+템플릿 뷰를 return
    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        // findViewById, setContentView -> Activity 만 가능
        // Activity 의 힘을 빌려 기능 사용 가능
        // -> Inflate ( Code -> 눈에 보이는 View 로 변환하는 작업 )
        // -> inflater 를 사용하여 Inflate 진행 ( 전역변수로 진행 )

        // p0 : 항목의 번호 ( position )
        // p1 : 이전의 만들어진 View ( xml 을 눈에 보이는 형태로 바꾼 것)
        //      -> 생성 전 inflate 한 View 가 있는지 확인
        // p2 : 모든 View, 항목을 가지고 있는 ListView


        var view = p1
        // p1 -> val 상태로 변수 사용 불가 새로운 변수 선언 후 p1 할당

        if(view == null){
            // Code -> View 로 변환
            // 들어가는 매개변수 LayOut,
            // 템플릿을 포함할 곳,
            // false ( 지금 생성하는 것들을 기존 속성과 일치시킬 건지 묻는 작업)

            view = inflater?.inflate(layOut,p2,false)
        }



        val tvName = view?.findViewById<TextView>(R.id.tvName)
        val tvTel = view?.findViewById<TextView>(R.id.tvTel)
        val img = view?.findViewById<ImageView>(R.id.img)
        val btnCall = view?.findViewById<Button>(R.id.btnCall)

        tvName?.text = data[p0].name
        tvTel?.text = data[p0].tel
        img?.setImageResource(data[p0].imgId)

        btnCall?.setOnClickListener {
            // 해당 전화번호로 전화걸기
            var tel = Uri.parse("tel:${data[p0].tel}")
            val intent = Intent(Intent.ACTION_DIAL,tel)

            // 새로운 Task ( Stack 통 ) 만들어 실행시키기
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)

        }

        // inflate 가 완료된 View( 데이터 + 템플릿 ) 리턴
        return view!!

    }


}