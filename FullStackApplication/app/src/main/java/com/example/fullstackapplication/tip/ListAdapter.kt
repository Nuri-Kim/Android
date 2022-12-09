package com.example.fullstackapplication.tip

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fullstackapplication.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class ListAdapter(var context: Context,
                  var data:ArrayList<ListVO>,
                  var keyData:ArrayList<String>,
                  var bookMarkList:ArrayList<String>)
    :RecyclerView.Adapter<ListAdapter.ViewHolder>(){

        val database = Firebase.database
        val auth : FirebaseAuth = Firebase.auth

        inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
            val tvTitle : TextView
            val imgContent : ImageView
            val imgMark : ImageView

            init {
                tvTitle = itemView.findViewById(R.id.tvTitle)
                imgContent = itemView.findViewById(R.id.imgContent)
                imgMark = itemView.findViewById(R.id.imgMark)
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        // context 에서 바로 빼는 작업
        val layoutInflater = LayoutInflater.from(context)
        //getSystemService -> 하드웨어가 가지고 있는 많은 센서 서비스 포함
        val view = layoutInflater.inflate(R.layout.list_template,null)

        return ViewHolder(view)


    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvTitle.text = data[position].title
        //holder.imgContent.setImageResource(data[position].imgId)
        Glide.with(context)
            .load(data[position].imgId)
            .into(holder.imgContent)

        // imgView 클릭 시 url 값 가지고 webViewActivity 로 이동

        holder.imgContent.setOnClickListener {
            val intetn = Intent(context,WebViewActivity::class.java)
            intetn.putExtra("url",data[position].url)

        context.startActivity(intetn)

        }

        if(bookMarkList.contains(keyData[position])){
            holder.imgMark.setImageResource(R.drawable.icon_marked)
        }else{
            holder.imgMark.setImageResource(R.drawable.icon_mark)
        }


        holder.imgMark.setOnClickListener {

            val bookMarkRef = database.getReference("bookMarkList")

                                // 로그인시에만 도달 가능 페이지
            bookMarkRef.child(auth.currentUser!!.uid)
                        .child(keyData[position]).setValue("good")

            // bookMarkList 에 해당 게시물이 이미 포함되어 있는지 확인

            if(bookMarkList.contains(keyData[position])){
            // bookMarkList 추가되어 있는 경우 ( 북마크 취소 )
                bookMarkRef.child(auth.currentUser!!.uid)
                    .child(keyData[position]).removeValue()

                holder.imgMark.setImageResource(R.drawable.icon_mark)
                Toast.makeText(context,"북마크에서 제거되었습니다",Toast.LENGTH_SHORT).show()
            }else{
            // bookMarkList 추가되어 있는 경우 ( 북마크 )
                bookMarkRef.child(auth.currentUser!!.uid)
                    .child(keyData[position]).setValue("good")

                //holder.imgMark.setImageResource(R.drawable.icon_marked)
                // adapter 실행되는 순간 북마크 되어있는 데이터는 북마크 된 상태로 출력

                Toast.makeText(context,"북마크에 추가되었습니다",Toast.LENGTH_SHORT).show()
            }


        }

    }

    override fun getItemCount(): Int {
        return data.size
    }


}