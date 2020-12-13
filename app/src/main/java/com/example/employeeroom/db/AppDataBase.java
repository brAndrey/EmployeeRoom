package com.example.employeeroom.db;


import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.employeeroom.db.model.Employee;

@Database(entities = {Employee.class},version = 1)
public abstract class AppDataBase extends RoomDatabase {
    public abstract EmployeeDao employeeDao();
}
