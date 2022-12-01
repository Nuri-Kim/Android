package com.example.ex221130

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class PokeAdapter2 (val context: Context, val pokeList : ArrayList<PokeVO>):
    RecyclerView.Adapter<PokeAdapter2.ViewHolder>() {

    // Java 에서는 onClickEvent 를 구현한다

    // inner Class 명시를 해야 Outer Class 의 member 접근 가능
    inner class ViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView) {
        val imgPoke : ImageButton
        val tvPokeLevel : TextView
        val tvPokeName : TextView
        val tvPokeType : TextView
        val btnRemove : Button

        init {
            imgPoke = itemView.findViewById(R.id.imgPoke)
            tvPokeLevel = itemView.findViewById(R.id.tvPokeLevel)
            tvPokeName = itemView.findViewById(R.id.tvPokeName)
            tvPokeType = itemView.findViewById(R.id.tvPokeType)
            btnRemove = itemView.findViewById(R.id.btnRemove)

            // 1. ListView 의 setOnItemClickListener
            btnRemove.setOnClickListener{
                // 해당 아이템의 position 정보가 필요함 -> adapterPosition 이 가지고 있음

                val position:Int = adapterPosition

                pokeList.removeAt(position)

                // Adapter 내 새로고침
                notifyDataSetChanged()
            }

            imgPoke.setOnClickListener{
                // position 전역변수 선언 X
                val position : Int = adapterPosition

                val message = "Lv : ${pokeList.get(position).level} /" +
                                "${pokeList.get(position).name}/" +
                            "Type : ${pokeList.get(position).type}"

                Toast.makeText(context,
                    message,Toast.LENGTH_SHORT).show()

            }

        }
    }

    // itemView 가 없을 때, ViewHolder 생성하는 파트
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val view = layoutInflater.inflate(R.layout.poke_list,null)

        return ViewHolder(view)
    }

    // 만들어진 ViewHolder 가 있다면, 꺼내서 쓰는 곳
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.imgPoke.setImageResource(pokeList.get(position).img)
        holder.tvPokeLevel.setText("Level : "+pokeList.get(position).level)
        holder.tvPokeName.setText(pokeList.get(position).name)
        holder.tvPokeType.setText("Type : "+pokeList.get(position).type)
    }

    override fun getItemCount(): Int {
        return pokeList.size
    }


}