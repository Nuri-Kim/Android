package com.example.fullstackapplication.board

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fullstackapplication.R
import java.net.URI

class BoardAdapter(val context: Context, val boardList:ArrayList<BoardVO>)
    :RecyclerView.Adapter<BoardAdapter.ViewHolder>() {


    // 없는 기능을 인터페이스를 생성하여 커스텀하여 사용
    // 리스너 커스텀
    interface OnItemClickListener{
        fun onItemClick(view : View, position: Int)
    }

    // 객체 저장 변수 선언
    lateinit var mOnItemClickListener:OnItemClickListener

    // 객체 전달 메서드
    fun setOnItemClickListener(onItemClickListener: OnItemClickListener){
        mOnItemClickListener = onItemClickListener
    }


    inner class ViewHolder(itemView: View)
        :RecyclerView.ViewHolder(itemView){

        val imgRv : ImageView
        val tvRvTitle : TextView
        val tvRvContent : TextView
        val tvRvTime : TextView

        init {
            imgRv = itemView.findViewById(R.id.imgRv)
            tvRvTitle = itemView.findViewById(R.id.tvRvTitle)
            tvRvContent = itemView.findViewById(R.id.tvRvContent)
            tvRvTime = itemView.findViewById(R.id.tvRvTime)

            itemView.setOnClickListener{
                val position = adapterPosition
                if(position != RecyclerView.NO_POSITION){
                    mOnItemClickListener.onItemClick(itemView,position)
                }
            }


        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val view = layoutInflater.inflate(R.layout.board_template,null)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.imgRv.setImageResource(R.drawable.profile_pic)
        holder.tvRvTitle.text=boardList[position].title
        holder.tvRvTime.text=boardList[position].time
        holder.tvRvContent.text=boardList[position].content

    }

    override fun getItemCount(): Int {
        return boardList.size
    }


}