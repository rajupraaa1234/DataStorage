package com.example.datastorage.ViewModel;

import android.content.Context;

import androidx.room.Room;

import com.example.datastorage.model.MyDataBase;
import com.example.datastorage.model.Student;

public class StudentViewModel {
    MyDataBase myDataBase;
    public StudentViewModel(Context context){
        myDataBase= Room.databaseBuilder(context,MyDataBase.class,"userdb").allowMainThreadQueries().build();
    }

    public void insertStudentData(Student student){
        myDataBase.myDAO().insert(student);
    }
}
