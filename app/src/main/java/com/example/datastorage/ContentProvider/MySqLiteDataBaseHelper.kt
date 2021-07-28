package com.example.datastorage.ContentProvider

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MySqLiteDataBaseHelper(context : Context) : SQLiteOpenHelper(context,"STUDENTDB",null,1) {
   lateinit var db1 : SQLiteDatabase


   override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE DETAIL_TABLE(_id INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT,DEPARTMENT TEXT)")
        if (db != null) {
            db1 = db
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        if (db != null) {
            db.execSQL(" DROP TABLE IF EXISTS " + "DETAIL_TABLE")
        }
        onCreate(db)
    }

    public fun insertData(stuName : String,dept : String){
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("NAME", stuName)
        contentValues.put("DEPARTMENT", dept)
        val result =
            db.insert("DETAIL_TABLE", null, contentValues)
    }

}