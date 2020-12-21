package com.example.employeeroom.repo;

import com.example.employeeroom.db.App;
import com.example.employeeroom.db.AppDataBase;
import com.example.employeeroom.db.model.Car;
import com.example.employeeroom.db.model.Employee;

public class RepositoryInUI {

    public void Insert(Employee employee){

        AppDataBase appDataBase = App.getInstance().getDatabase();
        appDataBase.employeeDao().insert(employee);

    }

    public long InsertId(Employee employee){

        AppDataBase appDataBase = App.getInstance().getDatabase();
       return appDataBase.employeeDao().insertId(employee);

    }

    //*********************************************

    public void Insert(Car car){

        AppDataBase appDataBase = App.getInstance().getDatabase();
        appDataBase.carDao().insert(car);

    }
}
