package com.example.datastorage.model;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MyDAO {

    @Insert
    public void insert(Student student);

    @Query("select * from users WHERE Uname = :name")
    Student getUser(String name);

}
