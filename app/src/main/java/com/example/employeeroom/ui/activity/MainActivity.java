package com.example.employeeroom.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.employeeroom.R;

import com.example.employeeroom.db.model.Car;
import com.example.employeeroom.repository.Repository;
import com.example.employeeroom.db.model.Employee;
import com.example.employeeroom.repo.RepositoryGet;
import com.example.employeeroom.utils.utils;

import java.util.List;

//import io.reactivex.Observable;
//import io.reactivex.Observer;


public class MainActivity extends AppCompatActivity {

    Repository repository;

private MainActivityViewModel mainActivityViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        repository = new Repository();

        mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);

        // liveData на employee
        mainActivityViewModel.getAllWords().observe(this, new Observer<List<Employee>>() {
            @Override
            public void onChanged(List<Employee> employees) {
               utils.PrintList(employees);
            }
        });

        // liveData на car
        mainActivityViewModel.getAllCar().observe(this,cars -> utils.PrintCar(cars));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Log.e(this.getClass().getSimpleName(),"Clear base");
            mainActivityViewModel.ClearBase();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void onReadProcess(View view) {

        RepositoryGet repositoryGet = new RepositoryGet();

        Log.d(this.getClass().getSimpleName(), " " + new Throwable().getStackTrace()[0].getMethodName() + "  " + Thread.currentThread().getName() + " " + System.currentTimeMillis());

        try {
            repositoryGet.getFromCallable();
            repositoryGet.getFromCallableCar();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void onSearchProcess(View view) {

        Log.d(this.getClass().getSimpleName(), " " + new Throwable().getStackTrace()[0].getMethodName() + "  " + Thread.currentThread().getName() + " " + System.currentTimeMillis());

        Employee employee = utils.newEmploe();

        mainActivityViewModel.InsertEmployee(employee);
    }

    public void onInsertProcess(View view) {

        Log.d(this.getClass().getSimpleName(), " " + new Throwable().getStackTrace()[0].getMethodName() + "  " + Thread.currentThread().getName() + " " + System.currentTimeMillis());

        Employee employee = utils.newEmploe();
        Car car = utils.newCar();

        mainActivityViewModel.InsertFromCallableOBS(employee,car);
        //mainActivityViewModel.InsertExecutorClass(employee,car);
    }
}