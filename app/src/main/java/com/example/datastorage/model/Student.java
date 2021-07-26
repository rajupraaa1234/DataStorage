package com.example.datastorage.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "users")
public class Student {
    @PrimaryKey(autoGenerate = true)

    @ColumnInfo(name = "id")
    public int id;

    @ColumnInfo(name = "Uname")
    public String Uname;

    public int getId() {
        return id;
    }

    public String getUseremail() {
        return Uname;
    }

    public void setUseremail(String uname) {
        Uname = uname;
    }
}
