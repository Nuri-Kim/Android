package com.example.fullstackapplication.tip

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fullstackapplication.R

class BookMarkAdapter(var context: Context,
                      var data : ArrayList<ListVO>,
                      var keyData : ArrayList<String>,
                      var bookMarkList : ArrayList<String>):
    RecyclerView.Adapter<BookMarkAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val imgMark :ImageView
        val imgContent : ImageView
        val tvTitle : TextView

            init {
                imgMark=itemView.findViewById(R.id.imgMark)
                imgContent=itemView.findViewById(R.id.imgContent)
                tvTitle=itemView.findViewById(R.id.tvTitle)
            }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // list_template.xml 을 눈에 보이는 view 객체로 변환

        val layoutInflater = LayoutInflater.from(context)
        val view = layoutInflater.inflate(R.layout.list_template, null)

        return ViewHolder(view)


    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // 북마크데이터에 포함되어있는지 판단 후 view + data 병합
        if (bookMarkList.contains(keyData[position])){
            holder.tvTitle.text=data[position].title

            Glide.with(context)
                .load(data[position].imgId)
                .into(holder.imgContent)
            
            // Glide -> 웹에 있는 이미지를 가져와서 세팅



        }
        if(bookMarkList.contains(keyData[position])){
            holder.imgMark.setImageResource(R.drawable.icon_marked)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }


}