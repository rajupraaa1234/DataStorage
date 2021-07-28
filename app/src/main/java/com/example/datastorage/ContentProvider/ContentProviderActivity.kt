package com.example.datastorage.ContentProvider

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.example.datastorage.R

class ContentProviderActivity : AppCompatActivity() {

    lateinit var cpListview: ListView
    lateinit var stuName : EditText
    lateinit var stuDept : EditText
    lateinit var addbtn : Button
    lateinit var mydb : MySqLiteDataBaseHelper
    lateinit var adapter: SimpleCursorAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content_provider)
        init()
        addbtn.setOnClickListener(View.OnClickListener {
            addDataInDB()
        })

        ContentProviderSetUp()
    }

    private fun addDataInDB() {
        var name = stuName.text.toString()
        var dept = stuDept.text.toString()

        if(name.isEmpty() || dept.isEmpty()){
            Toast.makeText(this,"Please enter all details",Toast.LENGTH_LONG).show()
            return
        }else{
            mydb.insertData(name,dept)
            stuName.setText("")
            stuDept.setText("")
            ContentProviderSetUp()
        }
    }

    private fun ContentProviderSetUp(){
        var fromColumn = arrayOf(MyContentProvider.NAME,MyContentProvider.DEPARTMENT)
        var rs:Cursor? = contentResolver.query(MyContentProvider.CONTENT_URI,null,null,null,null,null)
        var rowLayout = android.R.layout.simple_list_item_2
        var toTextView = intArrayOf(android.R.id.text1,android.R.id.text2)
        adapter = SimpleCursorAdapter(this,rowLayout,rs,fromColumn,toTextView,1)
        cpListview.adapter =adapter
    }

    private fun init(){
        cpListview = findViewById(R.id.cpListView)
        stuName = findViewById(R.id.stuName)
        stuDept = findViewById(R.id.stuDept)
        addbtn = findViewById(R.id.btn)
        mydb = MySqLiteDataBaseHelper(applicationContext)
    }
}