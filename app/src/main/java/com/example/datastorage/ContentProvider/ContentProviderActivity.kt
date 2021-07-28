package com.example.datastorage.ContentProvider

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.widget.SimpleCursorAdapter
import com.example.datastorage.R

class ContentProviderActivity : AppCompatActivity() {

    lateinit var cpListview: ListView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content_provider)

        cpListview = findViewById(R.id.cpListView)
        val uriSms: Uri = Uri.parse("content://sms/inbox")
        var fromColumn = arrayOf("body","address")
        var toTextView = intArrayOf(android.R.id.text1,android.R.id.text2)
        var rowLayout = android.R.layout.simple_list_item_2  //C:\Users\Admin\AppData\Local\Android\Sdk\platforms\android-28\data\res\layout
        var dataCursor =
            contentResolver.query(uriSms,null,null,null,null,null)

        var adapter: SimpleCursorAdapter =
            SimpleCursorAdapter(this,rowLayout,dataCursor,fromColumn,toTextView,1)
        cpListview.adapter =adapter
    }
}