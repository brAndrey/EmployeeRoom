package com.example.employeeroom.repo;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.employeeroom.db.App;
import com.example.employeeroom.db.AppDataBase;
import com.example.employeeroom.db.EmployeeDao;
import com.example.employeeroom.db.model.Car;
import com.example.employeeroom.db.model.Employee;

import java.util.List;

public class RepositotyLiveData {

    private EmployeeDao employeeDao;
    private LiveData<List<Employee>> mAllEmployee;

    private LiveData<List<Car>> mAllCar;

    public RepositotyLiveData(Application application){

        AppDataBase appDataBase = App.getInstance().getDatabase();
        mAllEmployee = appDataBase.employeeDao().getEmployee();

        mAllCar = appDataBase.carDao().getCar();

    }

   public LiveData<List<Employee>> gelAllEmployee(){
        return mAllEmployee;
    }

    public LiveData<List<Car>> getAllCar(){return  mAllCar; }



}
