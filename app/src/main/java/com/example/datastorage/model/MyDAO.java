package com.example.datastorage.model;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface MyDAO {

    @Insert
    public void insert(Student student);

    @Query("select * from users WHERE Uname = :name")
    Student getUser(String name);

    @Query("SELECT * FROM users WHERE Uname = :name")
    int isDataExist(String name);

    @Delete
    public void deleteUser(Student student);

    @Query("Select * from users")
    public List<Student> getData();

    @Update
    public void UpdateUser(Student student);

}
