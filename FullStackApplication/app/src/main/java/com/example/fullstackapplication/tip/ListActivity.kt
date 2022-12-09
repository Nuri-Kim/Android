package com.example.fullstackapplication.tip

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.RemoteViews
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fullstackapplication.R
import com.example.fullstackapplication.fragment.Fragment2
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class ListActivity : AppCompatActivity() {
    lateinit var adapter : ListAdapter

    /** 게시물의 UI 값이 들어가는 리스트*/
    var keyData = ArrayList<String>()
    var data = ArrayList<ListVO>()
    /** 북마크 경로 설정을 위한 선언 */
    lateinit var bookMarkRef : DatabaseReference
    /** BookMark 된 게시물의 정보가 들어갈 배열 */
    var bookMarkList = ArrayList<String>()

    var auth : FirebaseAuth=Firebase.auth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)



        val database = Firebase.database

        /** content getReference  */
        val allContent = database.getReference("content")
        bookMarkRef = database.getReference("bookMarkList")

        adapter = ListAdapter(this, data, keyData, bookMarkList)

        val postListener = object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                Log.d("스냅샷",snapshot.toString())
                Log.d("스냅샷2",snapshot.value.toString())
                // 데이터 받아오는 속도가 느림

                for ( model in snapshot.children){
                    val item = model.getValue(ListVO::class.java)

                    if(item != null){
                        data.add(item)
                    }
                    keyData.add(model.key.toString())
                }
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        }

        allContent.addValueEventListener(postListener)

        getBookMartData()

        val rvList = findViewById<RecyclerView>(R.id.rvList)
        val tvCategory = findViewById<TextView>(R.id.tvCategory)

        tvCategory.text = intent.getStringExtra("category")

        // TextView
        // RecyclerView -> Adapter, data(VO), template(xml)






        rvList.adapter = adapter

        rvList.layoutManager=GridLayoutManager(this,2)



    }

    // bookMarkList 에 저장되어있는 데이터 가져오기

    fun getBookMartData(){
        val postListener2 = object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

                bookMarkList.clear()

                for (model in snapshot.children){

                    /** 북마크 게시물의 uid 값을 bookMarkList에 저장 */
                    bookMarkList.add(model.key.toString())

                    Log.d("북마크",bookMarkList.toString())
                    Log.d("북마크사이즈",bookMarkList.size.toString())
                }

                adapter.notifyDataSetChanged()

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        }
            /** bookMarkList 에 저장된 데이터를 가져옴 */
            bookMarkRef.child(auth.currentUser!!.uid).addValueEventListener(postListener2)
    }




}