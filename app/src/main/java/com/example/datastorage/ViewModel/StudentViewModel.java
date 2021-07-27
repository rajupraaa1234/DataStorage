package com.example.datastorage.ViewModel;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.room.Room;

import com.example.datastorage.Repository.StudentRepository;
import com.example.datastorage.model.MyDataBase;
import com.example.datastorage.model.Student;

import java.util.List;

public class StudentViewModel extends AndroidViewModel {
    StudentRepository repository;

    public StudentViewModel(@NonNull Application application) {
        super(application);
//        myDataBase= Room.databaseBuilder(context,MyDataBase.class,"userdb").allowMainThreadQueries().build();
        repository = new StudentRepository(application);
    }


    public void insertStudentData(Student student){
        repository.insertStudentData(student);
    }

    public void deleteStudent(Student student){
        repository.deleteStudent(student);
    }

    public boolean searchStudent(String str){
       return repository.searchStudent(str);
    }

    public LiveData<List<Student>> getAllData(){
        return repository.getAllData();
    }

    public void updateData(Student student){
        repository.updateData(student);
    }
}
