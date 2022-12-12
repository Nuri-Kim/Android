package com.example.wordle

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.text.Editable
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnKeyListener
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView


class GameAdapter(val context: Context,
                  val gameList :ArrayList<GameVO>,
                  val answer : String):RecyclerView.Adapter<GameAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

        val etGame1 : EditText
        val etGame2 : EditText
        val etGame3 : EditText
        val etGame4 : EditText
        val etGame5 : EditText


        init{
            etGame1=itemView.findViewById(R.id.etGame1)
            etGame2=itemView.findViewById(R.id.etGame2)
            etGame3=itemView.findViewById(R.id.etGame3)
            etGame4=itemView.findViewById(R.id.etGame4)
            etGame5=itemView.findViewById(R.id.etGame5)

            val etList = ArrayList<EditText>()
            etList.add(etGame1)
            etList.add(etGame2)
            etList.add(etGame3)
            etList.add(etGame4)
            etList.add(etGame5)


            for (i in 0 until 4) {
                etList.get(i).setOnKeyListener(object : OnKeyListener {
                    override fun onKey(p0: View?, p1: Int, p2: KeyEvent?): Boolean {
                        if (p1 != 67 && p2?.action == KeyEvent.ACTION_DOWN) {
                            etList.get(i + 1).requestFocus()
                        }
                        return false
                    }
                })
            }


            etGame5.setOnKeyListener(object : OnKeyListener{
                override fun onKey(p0: View?, p1: Int, p2: KeyEvent?): Boolean {
                    Log.d("테스트",p0.toString())
                    Log.d("테스트",p1.toString())
                    Log.d("테스트",p2.toString())

                    // 66 -> Enter
                    // KeyDown = 0
                    // KeyUp = 1
                    if(p1 == 66 && p2?.action == KeyEvent.ACTION_UP){

                        checkAnswer(answer,etList)
                        disabledEditText(etList)

                        Log.d("정답",etList.toString())



                    }else{
                        //Toast.makeText(context,"엔터를 눌러 정답을 입력하세요",Toast.LENGTH_SHORT).show()
                    }


                    return false
                }

            })

        }

    }




    fun enabledEditText(etList: ArrayList<EditText>){
        for( i in 0 until etList.size){
            etList[i].isEnabled=true
        }
    }


    fun disabledEditText(etList: ArrayList<EditText>){
        for( i in 0 until etList.size){
            etList[i].isEnabled=false
        }
    }

    fun checkAnswer (answer : String, etList : ArrayList<EditText>){

        for(i in 0 until etList.size){
            val answerChar : Char = answer[i]
            val etChar : Char = etList[i].text.toString().single()

            if(answerChar == etChar){
                etList.get(i).setTextColor(context.resources.getColor(R.color.green, null))
            }else{
                var check = true

                for(j in 0 until etList.size){
                    if(etChar == answer.get(j)){

                        etList[i].setTextColor(context.resources.getColor(R.color.yellow, null))

                    check = false
                    }
                }
                if(check == true){
                    etList.get(i).setTextColor(context.resources.getColor(R.color.gray, null))
                }


            }



        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val view = layoutInflater.inflate(R.layout.game_list, null)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val etList = ArrayList<EditText>()
        etList.add(holder.etGame1)
        etList.add(holder.etGame2)
        etList.add(holder.etGame3)
        etList.add(holder.etGame4)
        etList.add(holder.etGame5)


        holder.etGame1.setText(gameList.get(position).word1)
        holder.etGame2.setText(gameList.get(position).word2)
        holder.etGame3.setText(gameList.get(position).word3)
        holder.etGame4.setText(gameList.get(position).word4)
        holder.etGame5.setText(gameList.get(position).word5)


        holder.etGame5.setOnKeyListener(object : OnKeyListener {
            override fun onKey(p0: View?, p1: Int, p2: KeyEvent?): Boolean {
                if (p1 == KeyEvent.KEYCODE_ENTER && p2?.action == KeyEvent.ACTION_UP){
                    if (position == gameList.size - 1) {

                        val intent = Intent(context,MainActivity::class.java)

                    } else {
                        checkAnswer(answer, etList)
                    }
                    disabledEditText(etList)
                }
                return false
            }
        })
    }


        override fun getItemCount(): Int {
            return gameList.size
        }


}

