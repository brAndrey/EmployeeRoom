package com.example.employeeroom.db;

import android.app.Application;

import androidx.room.Room;

public class App extends Application {

    public static App instanse;

    private AppDataBase database;

    @Override
    public void onCreate() {
        super.onCreate();
        instanse=this;
        database= Room.databaseBuilder(this, AppDataBase.class,"database")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();

    }

    public static App getInstance(){
        return instanse;
    }

    public AppDataBase getDatabase(){
        return database;
    }
}

