package com.example.fullstackapplication.fragment

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.findFragment
import com.example.fullstackapplication.ContactVO
import com.example.fullstackapplication.R
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase

class Fragment1 : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_1, container, false)
        val tvMsg = view.findViewById<TextView>(R.id.tvMsg)
        val clHome = view.findViewById<ConstraintLayout>(R.id.clHome)
        val etName = view.findViewById<EditText>(R.id.etName)
        val btnSend = view.findViewById<Button>(R.id.btnSend)

        val etContactName = view.findViewById<EditText>(R.id.etContactName)
        val etContactAge = view.findViewById<EditText>(R.id.etContactAge)
        val etContactTel = view.findViewById<EditText>(R.id.etContactTel)
        val btnContact = view.findViewById<Button>(R.id.btnContact)



        val yehoUrl = "https://iotchat-188fe-default-rtdb.firebaseio.com/"
        val yehoDb = Firebase.database(yehoUrl)
        val nulls = yehoDb.getReference("김누리")



        btnSend.setOnClickListener {
            val input = etName.text.toString()
            nulls.push().setValue(input)
            Toast.makeText(context,"눌림$input",Toast.LENGTH_SHORT).show()
        }

        // fireBase 에서 데이터를 실시간으로 읽고 쓸 수 있는
        // realTime dataBase -> NoSQL 형식 ( Key,Value 으로 이루어짐)

        // Write a message to the database
        //val database = Firebase.database // 연결된 fireBase 계정에 RealTime DB 연결)

        val url = "https://full-stack-f999e-default-rtdb.firebaseio.com/"
        val database = Firebase.database(url)

        val contact = database.getReference("contact2")

        btnContact.setOnClickListener {
            val name = etContactName.text.toString()
            val age = etContactAge.text.toString().toInt()
            val tel = etContactTel.text.toString()

            contact.push().setValue(ContactVO(name,age,tel))    }

        val myRef = database.getReference("message")
        myRef.setValue("Hello, World!")

        val color = database.getReference("color")

        color.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val bgColor = snapshot.getValue<String>()

                if(bgColor != null){
                    try{
                        clHome.setBackgroundColor(Color.parseColor(bgColor))
                    }catch (e : IllegalArgumentException){
                        clHome.setBackgroundColor(Color.parseColor("red"))
                    }catch (e : StringIndexOutOfBoundsException){
                        clHome.setBackgroundColor(Color.parseColor("black"))
                    }

                }else{
                    clHome.setBackgroundColor(Color.parseColor("white"))
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })



        // myRef 안의 값이 변하면 아래 메소드 실행
        myRef.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val message = snapshot.getValue<String>()
                tvMsg.setText(message)


            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })


        contact.addChildEventListener(object : ChildEventListener{
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                val webContact = snapshot.getValue<ContactVO>()
                Log.d("연락처",webContact.toString())

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