package com.example.fullstackapplication.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ImageView
import android.widget.TextView
import com.example.fullstackapplication.R
import com.example.fullstackapplication.tip.ListActivity

class Fragment2 : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_2, container, false)

        val imgAll = view.findViewById<ImageView>(R.id.imgAll)
        val imgCook = view.findViewById<ImageView>(R.id.imgCook)
        val imgLife = view.findViewById<ImageView>(R.id.imgLife)

        imgAll.setOnClickListener {
            // 클릭 시 listActivity 로 이동

            val intent = Intent(context,ListActivity::class.java)
            intent.putExtra("category",imgAll.tag.toString())

            startActivity(intent)
        }

        imgCook.setOnClickListener {
            // 클릭 시 listActivity 로 이동

            val intent = Intent(context,ListActivity::class.java)
            intent.putExtra("category",imgCook.tag.toString())

            startActivity(intent)
        }

        imgLife.setOnClickListener {
            // 클릭 시 listActivity 로 이동

            val intent = Intent(context,ListActivity::class.java)
            intent.putExtra("category",imgLife.tag.toString())

            startActivity(intent)
        }



        return view
    }


}