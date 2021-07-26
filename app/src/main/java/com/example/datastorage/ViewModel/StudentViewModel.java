package com.example.datastorage.ViewModel;

import android.content.Context;

import androidx.room.Room;

import com.example.datastorage.model.MyDataBase;
import com.example.datastorage.model.Student;

import java.util.List;

public class StudentViewModel {
    MyDataBase myDataBase;
    public StudentViewModel(Context context){
        myDataBase= Room.databaseBuilder(context,MyDataBase.class,"userdb").allowMainThreadQueries().build();
    }

    public void insertStudentData(Student student){
        myDataBase.myDAO().insert(student);
    }

    public void deleteStudent(Student student){
        myDataBase.myDAO().deleteUser(student);
    }

    public boolean searchStudent(String str){
        if(myDataBase.myDAO().isDataExist(str)==0){
            return false;
        }
        return true;
    }

    public List<Student> getAllData(){
        return myDataBase.myDAO().getData();
    }

    public void updateData(Student student){
        myDataBase.myDAO().UpdateUser(student);
    }
}
