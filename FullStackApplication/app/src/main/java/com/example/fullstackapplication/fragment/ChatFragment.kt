package com.example.fullstackapplication.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fullstackapplication.ChatVO
import com.example.fullstackapplication.R
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase


class ChatFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_chat, container, false)
        val etChat = view.findViewById<EditText>(R.id.etChat)
        val imgChatSend = view.findViewById<ImageView>(R.id.imgChatSend)
        val chatList = ArrayList<ChatVO>()


        val sp = activity?.getSharedPreferences("loginInfo",Context.MODE_PRIVATE)

        val loginId = sp?.getString("loginId","Unknown") as String

        val rvChat = view.findViewById<RecyclerView>(R.id.rvChat)


        val adapter = ChatAdapter(requireContext(), chatList, loginId)
        rvChat.adapter = adapter

        rvChat.layoutManager = LinearLayoutManager(requireContext())

        val db = Firebase.database
        val chatRef = db.getReference("chat")

        imgChatSend.setOnClickListener {
            val msg = etChat.text.toString()

            // Firebase RealTime DB 내 Chat 경로에 ChatVO Class 를 setVelue

            val chat = ChatVO(loginId,msg)

            chatRef.push().setValue(chat)
            etChat.text = null

        }

        chatRef.addChildEventListener(object :ChildEventListener{
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {

                val chatItem = snapshot.getValue<ChatVO>() as ChatVO

                chatList.add(chatItem)

                adapter.notifyDataSetChanged()

            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
                TODO("Not yet implemented")
            }

            override fun onChildRemoved(snapshot: DataSnapshot) {
                TODO("Not yet implemented")
            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
                TODO("Not yet implemented")
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })






        return view
    }


}