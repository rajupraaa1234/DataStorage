package com.example.datastorage.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.datastorage.MainActivity
import com.example.datastorage.R
import com.example.datastorage.model.Student

import com.example.myloginapp.interfacePackage.OnClickListner

class MyAdapter(context: MainActivity, data: ArrayList<Student>) :RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    var context : Context = context
    var arr : ArrayList<Student> = data
    var onclickItem : OnClickListner = context

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
       var textview : TextView = itemView.findViewById(R.id.txt)
        var delbtn : Button = itemView.findViewById<Button>(R.id.delbtn)
        var editbtn : Button = itemView.findViewById<Button>(R.id.editbtn)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.item_layout,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.textview.text = arr.get(position).Uname
        holder.textview.setOnClickListener(View.OnClickListener {
            onclickItem.onItemClick(position)
        })
        holder.delbtn.setOnClickListener(View.OnClickListener {
            onclickItem.onDeleteClick(position)
        })

        holder.editbtn.setOnClickListener(View.OnClickListener {
            onclickItem.onEditClick(position)
        })

    }

    override fun getItemCount(): Int {
       return arr.size
    }

}