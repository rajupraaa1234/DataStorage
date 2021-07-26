package com.example.datastorage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText

class MainActivity : AppCompatActivity() {

    lateinit var txt : EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    fun init(){
        txt = findViewById(R.id.txt)
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
        var sharPref = getSharedPreferences("mtap", MODE_PRIVATE)
        var editor = sharPref.edit()
        editor.putString("name",data)
        editor.apply()
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