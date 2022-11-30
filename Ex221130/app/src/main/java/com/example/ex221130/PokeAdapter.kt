package com.example.ex221130

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView


class PokeAdapter(val context: Context, val pokeList: ArrayList<PokeVO>) : BaseAdapter() {
    override fun getCount(): Int {
        return pokeList.size
    }

    override fun getItem(p0: Int): Any {
        return pokeList.get(p0)
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {

        val layOutInflater = LayoutInflater.from(context)

        var view = layOutInflater.inflate(R.layout.poke_list, null)

        var holder = ViewHolder()

        if( p1== null){
            Log.d("호출","1")
            holder.imgPoke = view.findViewById<ImageView>(R.id.imgPoke)
            holder.tvPokeLevel = view.findViewById<TextView>(R.id.tvPokeLevel)
            holder.tvPokeName = view.findViewById<TextView>(R.id.tvPokeName)
            holder.tvPokeType = view.findViewById<TextView>(R.id.tvPokeType)

            view.tag = holder
        }else{
            Log.d("호출","2")
            holder = p1.tag as ViewHolder
            view = p1
        }

        holder.imgPoke?.setImageResource(pokeList.get(p0).img)
        holder.tvPokeName?.setText(pokeList.get(p0).name)
        holder.tvPokeType?.setText("Type : "+pokeList.get(p0).type)
        holder.tvPokeLevel?.setText("Level : "+pokeList.get(p0).level)


        return view
    }

    // inner Class
    // 부모 클래스의 변수들을 다 사용할 수 있는 장점
    // 외부에서 이 inner Class 를 사용할 이유가 없을 때

    // ViewHolder pattern
    // getView 에 findViewById 로 접근한 정보들을
    // 저장하고 있는 class ViewHolder 를 만들어서
    // 메모리의 성능 향상

    class ViewHolder(){
        var imgPoke : ImageView? = null
        var tvPokeLevel : TextView? = null
        var tvPokeName : TextView? = null
        var tvPokeType : TextView? = null
    }



}
