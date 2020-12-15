package com.example.employeeroom.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.employeeroom.R;

import com.example.employeeroom.db.App;
import com.example.employeeroom.db.AppDataBase;
import com.example.employeeroom.repository.Repository;
import com.example.employeeroom.db.model.Employee;
import com.example.employeeroom.utils.utils;

public class MainActivity extends AppCompatActivity {

    AppDataBase appDataBase;

    Repository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        repository = new Repository();
        appDataBase = App.getInstance().getDatabase();

    }

    public void onInsertProcess(View view) {
        Employee employee = utils.newEmploe();

        Log.e(this.getClass().getSimpleName(), employee.toString());

        try {
            repository.InsertRX(employee);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Log.d(this.getClass().getSimpleName(), " " + new Throwable().getStackTrace()[0].getMethodName() + "  " + Thread.currentThread().getName() + " " + System.currentTimeMillis());

    }

    public void onReadProcess(View view) {
        Log.d(this.getClass().getSimpleName(), " " + new Throwable().getStackTrace()[0].getMethodName() + "  " + Thread.currentThread().getName() + " " + System.currentTimeMillis());

        try {
            repository.getFromCallable();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}