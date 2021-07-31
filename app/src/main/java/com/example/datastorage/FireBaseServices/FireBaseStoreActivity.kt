package com.example.datastorage.FireBaseServices

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.datastorage.Adapter.FireBaseAdapter
import com.example.datastorage.R
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot


class FireBaseStoreActivity : AppCompatActivity() {
    lateinit var db: FirebaseFirestore
    lateinit var name : EditText
    lateinit var last : EditText
    lateinit var born : EditText
    private var coursesArrayList: ArrayList<Person>? = null
    lateinit var adapter: FireBaseAdapter
    lateinit var recyclerView : RecyclerView
    companion object{
        val TAG = FireBaseStoreActivity::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fire_base_store)
        init()
    }

    private fun init(){
        db = FirebaseFirestore.getInstance()
        name = findViewById(R.id.name)
        last = findViewById(R.id.lastname)
        born = findViewById(R.id.born)
        recyclerView = findViewById(R.id.recycle)
    }

    fun sendDataFireStore(view: View) {
        var mname = name.text.toString()
        var mlast = last.text.toString()
        var mborn = born.text.toString()

        val user: MutableMap<String, Any> = HashMap()
        user["first"] = mname
        user["last"] = mlast
        user["born"] = mborn
        db.collection("users")
            .add(user)
            .addOnSuccessListener { documentReference ->
                Log.d(
                    FireBaseStoreActivity.TAG,
                    "DocumentSnapshot added with ID: " + documentReference.id
                )
            }
            .addOnFailureListener { e -> Log.w(FireBaseStoreActivity.TAG, "Error adding document", e) }
    }

    fun getDataFireStore(view : View){
        coursesArrayList = ArrayList()
        recyclerView.setLayoutManager(LinearLayoutManager(this))


        db.collection("users")
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    var obj :  Person = Person(document.data["first"] as String?,
                        document.data["last"] as String?, document.data["born"] as String?
                    )
                    coursesArrayList!!.add(obj)
                }
                adapter = FireBaseAdapter(coursesArrayList, this)
                recyclerView.setAdapter(adapter)
                adapter.notifyDataSetChanged();
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents: ", exception)
            }


//        db.collection("Courses").get()
//            .addOnSuccessListener(OnSuccessListener<QuerySnapshot>() {
//                @Override
//                fun onSuccess(QuerySnapshot queryDocumentSnapshots) {
//                    if (!queryDocumentSnapshots.isEmpty()) {
//                    //    loadingPB.setVisibility(View.GONE);
//                        var list = queryDocumentSnapshots.getDocuments();
//                       // List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
//                        for (d in list) {
//
//                            Person c = d.toObject(Person.class::java)
//                            coursesArrayList.add(c);
//                        }
//                        // after adding the data to recycler view.
//                        // we are calling recycler view notifuDataSetChanged
//                        // method to notify that data has been changed in recycler view.
//                        adapter.notifyDataSetChanged();
//                    } else {
//                        // if the snapshot is empty we are displaying a toast message.
//                        Toast.makeText(this, "No data found in Database", Toast.LENGTH_SHORT).show();
//                    }
//                }
//            }).addOnFailureListener(OnFailureListener() {
//                @Override
//                fun onFailure(@NonNull Exception e) {
//                    // if we do not get any data or any error we are displaying
//                    // a toast message that we do not get any data
//                    Toast.makeText(this, "Fail to get the data.", Toast.LENGTH_SHORT).show();
//                }
//            });
    }
}

