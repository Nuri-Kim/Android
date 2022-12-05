package com.example.ex20221202

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText

class Fragment4 : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_4, container, false)
        val etUrl = view.findViewById<EditText>(R.id.etUrl)
        val btnSend = view.findViewById<Button>(R.id.btnSend)

        btnSend.setOnClickListener {

            val url = etUrl.text.toString()

            // Activity 가 아니기 때문에 requireActivity 의 힘을 빌려야 함
            val spf = requireActivity().getSharedPreferences("mySPF",Context.MODE_PRIVATE)

            // editor 가져오기
            val editor = spf.edit()

            // editor 이용하여 mySPF 값 넣기
            editor.putString("url",url)

            editor.commit()




        }


        return view

    }


}