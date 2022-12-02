package com.example.ex20221201

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
class MovieAdapter(val context: Context, val movieList : ArrayList<MovieVO>):
RecyclerView.Adapter<MovieAdapter.ViewHolder>(){

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        val tvRank : TextView
        val tvOldAndNew : TextView
        val tvMovieNm : TextView
        val tvAudiAcc : TextView
        val tvAudiCnt : TextView
        val tvOpenDt : TextView



        init{
            tvRank = itemView.findViewById(R.id.tvRank)
            tvOldAndNew = itemView.findViewById(R.id.tvOldAndNew)
            tvMovieNm = itemView.findViewById(R.id.tvMovieNm)
            tvAudiAcc = itemView.findViewById(R.id.tvAudiAcc)
            tvAudiCnt = itemView.findViewById(R.id.tvAudiCnt)
            tvOpenDt = itemView.findViewById(R.id.tvOpenDt)


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val view = layoutInflater.inflate(R.layout.movie_list,null)


        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val rank : String = movieList.get(position).rank
        val rankOldAndNew : String = movieList.get(position).rankOldAndNew
        val movieNm : String = movieList.get(position).movieNm
        val audiAcc : String = movieList.get(position).audiAcc
        val AudiCnt : String = movieList.get(position).audiCnt
        val openDt : String = movieList.get(position).openDt

        holder.tvRank.text =  rank
        holder.tvOldAndNew.text = rankOldAndNew
        holder.tvMovieNm.text = movieNm
        holder.tvAudiAcc.text = audiAcc
        holder.tvAudiCnt.text = AudiCnt
        holder.tvOpenDt.text = openDt


    }

    override fun getItemCount(): Int {
        return movieList.size

    }
}