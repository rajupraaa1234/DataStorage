package com.example.datastorage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import androidx.room.Room.databaseBuilder
import com.example.datastorage.Adapter.MyAdapter
import com.example.datastorage.ContentProvider.ContentProviderActivity
import com.example.datastorage.ViewModel.StudentViewModel
import com.example.datastorage.model.MyDataBase
import com.example.datastorage.model.Student
import com.example.myloginapp.Dialog.ExampleDialog
import com.example.myloginapp.interfacePackage.OnClickListner
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging

class MainActivity : AppCompatActivity(), OnClickListner, ExampleDialog.ExampleDialogListener{

    lateinit var txt : EditText
    lateinit var mydataBase : MyDataBase
    var TAG = MainActivity::class.java.simpleName
    lateinit var studantTable : StudentViewModel
    lateinit var adapter: MyAdapter
    lateinit var recyclerView : RecyclerView
    lateinit var nodata : TextView
    var arr :ArrayList<Student> = ArrayList<Student>();
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
        initFireBase()
    }

    private fun initFireBase() {
        FirebaseMessaging.getInstance().setAutoInitEnabled(true);
    }

    override fun onStart() {
        super.onStart()

        FirebaseMessaging.getInstance().token
            .addOnCompleteListener(OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    Log.w(TAG, "Fetching FCM registration token failed", task.exception)
                    return@OnCompleteListener
                }

                // Get new FCM registration token
                val token: String = task.getResult().toString()

                // Log and toast
                //val msg = getString(R.string.msg_token_fmt, token)
                Log.d(TAG, token)
                Toast.makeText(this, token, Toast.LENGTH_SHORT).show()
            })
    }

    fun init(){
        adapter = MyAdapter(this,arr)
        txt = findViewById(R.id.txt)
        studantTable = StudentViewModel(this)
        recyclerView =  findViewById(R.id.recycle)
        nodata = findViewById(R.id.nodata)

        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = MyAdapter(this,arr)
        recyclerView.adapter = adapter
        FetchAlldataFromRoom()
        checkForData()
    }

    private fun FetchAlldataFromRoom() {
        var data   = ArrayList<Student>()
        data = studantTable.getAllData() as ArrayList<Student>
        for(i in data){
            arr.add(i)
        }
    }

    fun onClickHandler(view : View){
        when(view.id){
            R.id.button->{
                onSaveData()
            }
            R.id.search->{onSearch()}
            R.id.cp->{onDelete()}
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
        var cpIntent : Intent
        cpIntent = Intent(this,ContentProviderActivity::class.java)
        startActivity(cpIntent)
    }

    private fun onSearch() {
        var data = txt.text.toString()
        if(studantTable.searchStudent(data)==true){
            Toast.makeText(this,"Student Found",Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(this,"Student Not Found",Toast.LENGTH_SHORT).show()
        }
    }

    private fun onSaveData() {
        var data = txt.text.toString()
        var stu : Student = Student()
        stu.useremail = data
        studantTable.insertStudentData(stu)
        arr.add(stu)
        adapter.notifyDataSetChanged()
        checkForData()
    }

  /*  override fun onResume() {
        super.onResume()
        restoreData()
    }*/

    private fun restoreData() {
        var sharPref = getSharedPreferences("mtap", MODE_PRIVATE)
        var data = sharPref.getString("name","")
        txt.setText(data)
    }

    override fun onItemClick(i: Int) {
        TODO("Not yet implemented")
    }

    override fun onDeleteClick(i: Int) {
        studantTable.deleteStudent(arr.get(i))
        arr.removeAt(i)
        adapter.notifyDataSetChanged()
        checkForData()
    }

    override fun onEditClick(i: Int) {
        var exampleDialog : ExampleDialog = ExampleDialog(i,arr.get(i).Uname);
        exampleDialog.show(getSupportFragmentManager(), "example dialog");
    }

    fun checkForData(){
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
        studantTable.updateData(data)
        adapter.notifyDataSetChanged()
    }
}