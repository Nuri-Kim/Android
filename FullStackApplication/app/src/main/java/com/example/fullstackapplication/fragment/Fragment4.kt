package com.example.fullstackapplication.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fullstackapplication.R
import com.example.fullstackapplication.tip.BookMarkAdapter
import com.example.fullstackapplication.tip.ListVO
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class Fragment4 : Fragment() {

    val auth : FirebaseAuth = Firebase.auth
    val database = Firebase.database
    val contentRef = database.getReference("content")
    val bookMarkRef = database.getReference("bookMarkList")

    var data = ArrayList<ListVO>()
    var keyData = ArrayList<String>()
    var bookMarkList = ArrayList<String>()

    lateinit var adapter : BookMarkAdapter



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_4, container, false)

        val rvBookMark = view.findViewById<RecyclerView>(R.id.rvBookMark)

        // content 데이터 전부 호출
        //getContentData()

        // 사용자가 북마크한 정보 가져오기
        getBookmarkData()

        // 사용자가 북마크한 정보만 rvBookMark 에 반영
        adapter = BookMarkAdapter(requireActivity(),data,keyData,bookMarkList)

        // adapter 적용
        rvBookMark.adapter=adapter
        rvBookMark.layoutManager=GridLayoutManager(requireContext(),2)



        return view
    }

    fun getContentData(){
        // content 경로에 있는 데이더 전부 호출
        //uid --> keyData 에 담기
        // ListVo --> data

        val posterListener = object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                for(model in snapshot.children){
                    val item = model.getValue(ListVO::class.java)

                    if ( bookMarkList.contains(model.key.toString())){
                        if (item != null) {
                            data.add(item)
                        }
                        keyData.add(model.key.toString())
                    }


                }
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        }
        contentRef.addValueEventListener(posterListener)
        // snapshot -> content 경로에 있는 데이터 전부가 들어옴


   }




    fun getBookmarkData(){
        // content 경로에 있는 데이더 전부 호출
        // 게시물의 uid 값 --> bookMarkList 에 담기

        val posterListener2 = object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                bookMarkList.clear()
                for(model in snapshot.children){
                    bookMarkList.add(model.key.toString())
                }
                adapter.notifyDataSetChanged()
                getContentData()
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        }
        bookMarkRef.child(auth.currentUser!!.uid)
            .addValueEventListener(posterListener2)




   }





}