package com.example.ex20221201

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.interpolator.view.animation.LinearOutSlowInInterpolator
import androidx.recyclerview.widget.RecyclerView
import java.security.AllPermission

class WeatherAdapter(val context: Context,val weatherList : ArrayList<WeatherVO>):RecyclerView.Adapter<WeatherAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val tvCity : TextView
        val tvState : TextView
        val tvTemp : TextView
        val tvTempMin : TextView
        val tvTempMax : TextView
        val tvHumidity : TextView
        val tvSpeed : TextView

        init {
            tvCity = itemView.findViewById(R.id.tvCity)
            tvState = itemView.findViewById(R.id.tvState)
            tvTemp = itemView.findViewById(R.id.tvTemp)
            tvTempMin = itemView.findViewById(R.id.tvTempMin)
            tvTempMax = itemView.findViewById(R.id.tvTempMax)
            tvHumidity = itemView.findViewById(R.id.tvHumidity)
            tvSpeed = itemView.findViewById(R.id.tvSpeed)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val view = layoutInflater.inflate(R.layout.weather_list,null)

        return ViewHolder(view)


    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.tvCity.setText(weatherList.get(position).city)
        holder.tvState.setText(weatherList.get(position).state)
        holder.tvTemp.setText(weatherList.get(position).temp+"C˚")
        holder.tvTempMin.setText("Min "+weatherList.get(position).temp_min+"C˚")
        holder.tvTempMax.setText("Max "+weatherList.get(position).temp_max+"C˚")
        holder.tvHumidity.setText(weatherList.get(position).humidity)
        holder.tvSpeed.setText(weatherList.get(position).speed)

    }

    override fun getItemCount(): Int {
        return weatherList.size
    }
}