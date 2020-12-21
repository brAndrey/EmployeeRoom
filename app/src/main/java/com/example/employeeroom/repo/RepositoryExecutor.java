package com.example.employeeroom.repo;

import android.util.Log;

import com.example.employeeroom.db.App;
import com.example.employeeroom.db.AppDataBase;
import com.example.employeeroom.db.model.Car;
import com.example.employeeroom.db.model.Employee;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class RepositoryExecutor {

    public void InsertExecutor(Employee employee) {

        // https://stackoverflow.com/questions/46482423/android-room-asynctask

//        executor.execute(() -> {
//            appDataBase.employeeDao().insert(employee);
//        });

        AppDataBase appDataBase = App.getInstance().getDatabase();

        Executor executor = Executors.newSingleThreadExecutor();

        executor.execute(new Runnable() {
            @Override
            public void run() {
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                Log.d(this.getClass().getSimpleName(), " "+ new Throwable().getStackTrace()[0].getMethodName() +"  "+ Thread.currentThread().getName()+" "+System.currentTimeMillis());
                //appDataBase.employeeDao().insert(employee);
                Long EmpId = appDataBase.employeeDao().insertId(employee);
                Log.d(this.getClass().getSimpleName(),"EmpId "+EmpId);
            }
        });
    }

    public void InsertExecutor(Car car) {
        AppDataBase appDataBase = App.getInstance().getDatabase();

        Executor    executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            appDataBase.carDao().insert(car);
        });
    }

    //*******************************************************************
}
