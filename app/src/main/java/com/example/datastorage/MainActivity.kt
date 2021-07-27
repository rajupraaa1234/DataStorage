package com.example.datastorage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import androidx.room.Room.databaseBuilder
import com.example.datastorage.Adapter.MyAdapter
import com.example.datastorage.ViewModel.StudentViewModel
import com.example.datastorage.model.MyDataBase
import com.example.datastorage.model.Student
import com.example.myloginapp.Dialog.ExampleDialog
import com.example.myloginapp.interfacePackage.OnClickListner
import java.time.Duration.of
import java.time.Month.of
import java.util.EnumSet.of
import java.util.Optional.of
import java.util.OptionalInt.of

class MainActivity : AppCompatActivity(), OnClickListner, ExampleDialog.ExampleDialogListener{

    lateinit var txt : EditText
    lateinit var mydataBase : MyDataBase

    lateinit var adapter: MyAdapter
    lateinit var recyclerView : RecyclerView
    lateinit var nodata : TextView
    lateinit var mViewmodel : StudentViewModel
    var arr :ArrayList<Student> = ArrayList<Student>();
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    fun init(){
        adapter = MyAdapter(this,arr)
        txt = findViewById(R.id.txt)

        recyclerView =  findViewById(R.id.recycle)
        nodata = findViewById(R.id.nodata)

        mViewmodel = ViewModelProviders.of(this).get(StudentViewModel::class.java)

        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = MyAdapter(this,arr)
        recyclerView.adapter = adapter
        FetchAlldataFromRoom()

        Handler(Looper.getMainLooper()).postDelayed({
            checkForData()
        }, 3000)

    }

    private fun FetchAlldataFromRoom() {

        var data   = ArrayList<Student>()
        mViewmodel!!.allData.observe(this,  Observer<List<Student>>{
            data = it as ArrayList<Student>
            arr = data
            Log.i("MySize"," "+data.size)
        })

        //checkForData()

        //adapter.notifyDataSetChanged()
    }

    fun onClickHandler(view : View){
        when(view.id){
            R.id.button->{
                onSaveData()
            }
            R.id.search->{onSearch()}
            R.id.delete->{onDelete()}
        }
    }

    private fun onDelete() {
//        var data = txt.text.toString()
//        var stu : Student = Student()
//        stu.useremail = data
//        if(studantTable.searchStudent(data)==true){
//            studantTable.deleteStudent(data)
//            Toast.makeText(this,"Student Student Successfully",Toast.LENGTH_SHORT).show()
//        }else{
//            Toast.makeText(this,"Student Not Found",Toast.LENGTH_SHORT).show()
//        }

    }

    private fun onSearch() {
        var data = txt.text.toString()
        if(mViewmodel.searchStudent(data)==true){
            Toast.makeText(this,"Student Found",Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(this,"Student Not Found",Toast.LENGTH_SHORT).show()
        }
    }

    private fun onSaveData() {
        var data = txt.text.toString()
        var stu : Student = Student()
        stu.useremail = data
        mViewmodel.insertStudentData(stu)
        arr.add(stu)
        adapter.notifyDataSetChanged()
        checkForData()
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

    override fun onItemClick(i: Int) {
        TODO("Not yet implemented")
    }

    override fun onDeleteClick(i: Int) {
        mViewmodel.deleteStudent(arr.get(i))
        arr.removeAt(i)
        adapter.notifyDataSetChanged()
        checkForData()
    }

    override fun onEditClick(i: Int) {
        var exampleDialog : ExampleDialog = ExampleDialog(i,arr.get(i).Uname);
        exampleDialog.show(getSupportFragmentManager(), "example dialog");
    }

    fun checkForData(){
        Log.i("MySize2"," " +arr.size)
        if(arr.size==0){
            recyclerView.visibility =View.GONE
            nodata.visibility = View.VISIBLE
        }else{
            recyclerView.visibility =View.VISIBLE
            nodata.visibility = View.GONE
        }
    }

    override fun applyTexts(username: String?,position : Int){
        var data = arr.get(position)
        data.Uname = username
        mViewmodel.updateData(data)
        adapter.notifyDataSetChanged()
    }
}