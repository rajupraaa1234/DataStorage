package com.example.datastorage.ContentProvider

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.datastorage.R

class ContentProviderActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content_provider)

        var mydb = MySqLiteDataBaseHelper(applicationContext)
        mydb.insertData("Raju kumar gupta","MCA")
        mydb.insertData("Rahul kumar gupta","BCA")
        mydb.insertData("vikash kumar gupta","BBA")
        var db : SQLiteDatabase = mydb.readableDatabase
        var rs : Cursor = db.rawQuery("SELECT * FROM DETAIL_TABLE",null)
        if(rs.moveToFirst()){
            Toast.makeText(applicationContext,rs.getString(1) + "\n" + rs.getString(2),Toast.LENGTH_LONG).show()
        }
    }
}