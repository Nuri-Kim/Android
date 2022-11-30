package com.example.ex20221129

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class SubActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub)

        val etReult = findViewById<EditText>(R.id.etResult)
        val btnSend = findViewById<Button>(R.id.btnSend)

        btnSend.setOnClickListener {

            val str = etReult.text.toString()

            val intent = Intent()
            intent.putExtra("content",str)

            setResult(RESULT_OK, intent)

            finish()
        }
    }
}