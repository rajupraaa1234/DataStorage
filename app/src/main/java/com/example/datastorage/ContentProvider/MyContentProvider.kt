package com.example.datastorage.ContentProvider

import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.net.Uri

class MyContentProvider : ContentProvider() {

    companion object{
        val PROVIDER_NAME = "com.example.datastorage.ContentProvider/MyContentProvider"
        val URL = "content://$PROVIDER_NAME/DETAIL_TABLE"
        val CONTENT_URI : Uri = Uri.parse(URL)

        val _ID = "_id"
        val NAME = "NAME"
        val  DEPARTMENT = "DEPARTMENT"
    }

    lateinit var db : SQLiteDatabase
    override fun onCreate(): Boolean {
        var helper = context?.let { MySqLiteDataBaseHelper(it) }
        if (helper != null) {
            db = helper.writableDatabase
        }
        return if(db==null) false else true
    }

    override fun query(
        uri: Uri,
        cols: Array<out String>?,
        conditions: String?,
        conditions_val: Array<out String>?,
        order: String?
    ): Cursor? {
       return db.query("DETAIL_TABLE",cols,conditions,conditions_val,null,null,order)
    }

    override fun getType(uri: Uri): String? {
        return "vnd.android.cursor.dir/vnd.example.detail_table"
    }

    override fun insert(uri: Uri, cv: ContentValues?): Uri? {
        db.insert("DETAIL_TABLE",null,cv)
        context?.contentResolver?.notifyChange(uri,null)
        return uri
    }

    override fun delete(p0: Uri, p1: String?, p2: Array<out String>?): Int {
        TODO("Not yet implemented")
    }

    override fun update(p0: Uri, p1: ContentValues?, p2: String?, p3: Array<out String>?): Int {
        TODO("Not yet implemented")
    }
}