package com.example.datastorage.Repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.datastorage.model.MyDAO;
import com.example.datastorage.model.MyDataBase;
import com.example.datastorage.model.Student;

import java.util.List;

public class StudentRepository {
    private MyDAO myDAO;
    private LiveData<List<Student>> mAllWords;

    public StudentRepository(Application application) {
        MyDataBase db = MyDataBase.getDatabase(application);
        myDAO = db.wordDao();
//        mAllWords = mWordDao.getAllWords();
    }

    public void insertStudentData(Student student){
        myDAO.insert(student);
    }

    public void deleteStudent(Student student){
        myDAO.deleteUser(student);
    }

    public boolean searchStudent(String str){
        if(myDAO.isDataExist(str)==0){
            return false;
        }
        return true;
    }

    public LiveData<List<Student>> getAllData(){
        return myDAO.getData();
    }

    public void updateData(Student student){
        myDAO.UpdateUser(student);
    }

}
