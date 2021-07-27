package com.example.datastorage.model;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Student.class},version = 1)
public abstract class MyDataBase extends RoomDatabase {

    public abstract MyDAO wordDao();

    private static MyDataBase INSTANCE;


    public static MyDataBase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (MyDataBase.class) {
                if (INSTANCE == null)    {
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            MyDataBase.class, "userdb")
                            // .addCallback(sOnOpenCallback)
                            .allowMainThreadQueries().build();

                }}}
        return INSTANCE;
    }


    //public abstract MyDAO myDAO();
}
