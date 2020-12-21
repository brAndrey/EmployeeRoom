package com.example.employeeroom.db;


import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.employeeroom.db.model.Car;
import com.example.employeeroom.db.model.Employee;

@Database(entities = {Employee.class, Car.class},version = 3)
public abstract class AppDataBase extends RoomDatabase {
    public abstract EmployeeDao employeeDao();
    public abstract CarDao carDao();
}
