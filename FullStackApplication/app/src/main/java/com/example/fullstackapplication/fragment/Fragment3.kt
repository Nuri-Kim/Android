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
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fullstackapplication.R
import com.example.fullstackapplication.board.BoardAdapter
import com.example.fullstackapplication.board.BoardInsideActivity
import com.example.fullstackapplication.board.BoardVO
import com.example.fullstackapplication.board.BoardWriteActivity
import com.example.fullstackapplication.utils.FBdataBase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class Fragment3 : Fragment() {

    var boardList = ArrayList<BoardVO>()
    lateinit var adapter : BoardAdapter
    var keyData = ArrayList<String>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_3, container, false)

        // btnWrite 클릭 시 board.BoardWriteActivity 로 이동
        val rvBoard = view.findViewById<RecyclerView>(R.id.rvBoard)
        val btnWrite = view.findViewById<Button>(R.id.btnWrite)

        btnWrite.setOnClickListener{
            val intent = Intent(requireActivity(),BoardWriteActivity::class.java)
            requireActivity().startActivity(intent)
        }

        getBoardData()

        adapter = BoardAdapter(requireContext(),boardList)
        rvBoard.adapter = adapter
        rvBoard.layoutManager=LinearLayoutManager(requireContext())

        // 클릭 이벤트 호출

        adapter.setOnItemClickListener(object : BoardAdapter.OnItemClickListener{
            override fun onItemClick(view: View, position: Int) {
                // 항목을 누르면 이동하기 위해 항목 위치 정보 가져오는 함수

                val intent = Intent(requireContext(),BoardInsideActivity::class.java)
                intent.putExtra("title",boardList[position].title)
                intent.putExtra("time",boardList[position].time)
                intent.putExtra("content",boardList[position].content)
                intent.putExtra("key",keyData[position])

                startActivity(intent)
            }

        })

        return view
    }
    // board 에 있는 데이터 전부 가져오기
    fun getBoardData(){
        val postListener = object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                // Firebase 에서 snapshot 으로 데이터를 받아온 경우
                // 게시물의 uid, BoardVO 전달 받음
                boardList.clear()
                for(model in snapshot.children){
                    val item = model.getValue(BoardVO::class.java)
                    if (item != null) {
                        boardList.add(item)
                    }
                    keyData.add(model.key.toString())
                }
                // 최근 업로드한 게시물 위에 보내기
                boardList.reverse()
                keyData.reverse()

                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                // 오류가 발생했을 경우
            }

        }
        // Board 에 있는 모든 데이터 Firebase 에서 가져와서 전송
        FBdataBase.getBoardRef().addValueEventListener(postListener)

    }


}