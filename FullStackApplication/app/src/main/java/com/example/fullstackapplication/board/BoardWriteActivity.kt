package com.example.fullstackapplication.board

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isInvisible
import com.example.fullstackapplication.R
import com.example.fullstackapplication.utils.FBAuth
import com.example.fullstackapplication.utils.FBdataBase
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import java.io.ByteArrayOutputStream
import java.net.URI

class BoardWriteActivity : AppCompatActivity() {

    lateinit var imgLoad : ImageView
    lateinit var tvimg : TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_board)


        imgLoad = findViewById(R.id.imgLoad)
        val etTitle = findViewById<EditText>(R.id.etTitle)
        val etContent = findViewById<EditText>(R.id.etContent)
        val imgWrite = findViewById<ImageView>(R.id.imgWrite)
        tvimg =  findViewById(R.id.tvimg)

        imgWrite.setOnClickListener{
            val title = etTitle.text.toString()
            val content = etContent.text.toString()

            //FBdataBase.
            //getBoardRef().
            //push().setValue(BoardVO("1","1","1","1"))

            val uid = FBAuth.getUid()
            val time = FBAuth.getTime()

            var key = FBdataBase.getBoardRef().push().key.toString()

            FBdataBase.
            getBoardRef().child(key).
            setValue(BoardVO(title,content,uid,time))
            imgUpload(key)

            finish()

        }

        imgLoad.setOnClickListener{
            val intent = Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            launcher.launch(intent)
        }

    }

    fun imgUpload(key:String){

        val storage = Firebase.storage
        val storageRef = storage.reference
        val mountainsRef = storageRef.child("$key.png")

        // Get the data from an ImageView as bytes
        imgLoad.isDrawingCacheEnabled = true
        imgLoad.buildDrawingCache()
        val bitmap = (imgLoad.drawable as BitmapDrawable).bitmap
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, baos)
        val data = baos.toByteArray()

        var uploadTask = mountainsRef.putBytes(data)
        uploadTask.addOnFailureListener {
            // Handle unsuccessful uploads
        }.addOnSuccessListener { taskSnapshot ->
            // taskSnapshot.metadata contains file metadata such as size, content-type, etc.
            // ...
        }
    }




    // Launcher 만들기

    val launcher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()){

        if(it.resultCode == RESULT_OK){
            imgLoad.setImageURI(it.data?.data)
            tvimg.isInvisible = true
        }



    }




}