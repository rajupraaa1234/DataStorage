package com.example.datastorage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.room.Room
import androidx.room.Room.databaseBuilder
import com.example.datastorage.ViewModel.StudentViewModel
import com.example.datastorage.model.MyDataBase
import com.example.datastorage.model.Student

class MainActivity : AppCompatActivity() {

    lateinit var txt : EditText
    lateinit var mydataBase : MyDataBase
    lateinit var studantTable : StudentViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    fun init(){
        txt = findViewById(R.id.txt)
        studantTable = StudentViewModel(this)
    }
    fun onClickHandler(view : View){
        when(view.id){
            R.id.button->{
                onSaveData()
            }
        }
    }

    private fun onSaveData() {
        var data = txt.text.toString()
        var stu : Student = Student()
        stu.useremail = data
        studantTable.insertStudentData(stu)
//        var sharPref = getSharedPreferences("mtap", MODE_PRIVATE)
//        var editor = sharPref.edit()
//        editor.putString("name",data)
//        editor.apply()
    }

    override fun onResume() {
        super.onResume()
        restoreData()
    }

    private fun restoreData() {
        var sharPref = getSharedPreferences("mtap", MODE_PRIVATE)
        var data = sharPref.getString("name","")
        txt.setText(data)
    }
}