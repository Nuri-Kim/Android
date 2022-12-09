package com.example.fullstackapplication.fragment

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fullstackapplication.ChatVO
import com.example.fullstackapplication.R

class ChatAdapter (val context: Context,
                   val chatList : ArrayList<ChatVO>,
                   val loginId : String) : RecyclerView.Adapter<ChatAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

        val imgChat : ImageView
        val tvChatOtherName : TextView
        val tvChatOtherMsg : TextView
        val tvChatMyMsg : TextView

        init{
            imgChat = itemView.findViewById(R.id.imgChat)
            tvChatOtherName = itemView.findViewById(R.id.tvChatOtherName)
            tvChatOtherMsg = itemView.findViewById(R.id.tvChatOtherMsg)
            tvChatMyMsg = itemView.findViewById(R.id.tvChatMyMsg)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val layoutInflater = LayoutInflater.from(context)
        val view = layoutInflater.inflate(R.layout.chat_list,null)

        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


        val name = chatList[position].name

        if(loginId == name) {
            holder.imgChat.visibility=View.GONE
            holder.tvChatOtherName.visibility=View.GONE
            holder.tvChatOtherMsg.visibility=View.GONE

            holder.tvChatMyMsg.visibility=View.VISIBLE

            holder.tvChatMyMsg.text=chatList[position].msg

        }else{
            holder.imgChat.visibility=View.VISIBLE
            holder.tvChatOtherName.visibility=View.VISIBLE
            holder.tvChatOtherMsg.visibility=View.VISIBLE

            holder.tvChatMyMsg.visibility=View.GONE

            holder.imgChat.setImageResource(R.drawable.profile_pic)
            holder.tvChatOtherName.text=chatList[position].name
            holder.tvChatOtherMsg.text=chatList[position].msg
        }

    }

    override fun getItemCount(): Int {
        return chatList.size
    }


}