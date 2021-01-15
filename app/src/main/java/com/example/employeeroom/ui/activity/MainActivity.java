package com.example.employeeroom.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.employeeroom.R;

import com.example.employeeroom.db.model.Car;
import com.example.employeeroom.repository.Repository;
import com.example.employeeroom.db.model.Employee;
import com.example.employeeroom.repo.RepositoryExecutor;
import com.example.employeeroom.repo.RepositoryGet;
import com.example.employeeroom.repo.RepositoryInUI;
import com.example.employeeroom.utils.utils;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    Repository repository;

private MainActivityViewModel mainActivityViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        repository = new Repository();

        mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);

//        // liveData на employee
//        mainActivityViewModel.getAllWords().observe(this, new Observer<List<Employee>>() {
//            @Override
//            public void onChanged(List<Employee> employees) {
//                utils.PrintList(employees);
//            }
//        });
//
//        // liveData на car
//        mainActivityViewModel.getAllCar().observe(this,cars -> utils.PrintCar(cars));

    }

    public void onInsertProcess(View view) {

        Log.d(this.getClass().getSimpleName(), " " + new Throwable().getStackTrace()[0].getMethodName() + "  " + Thread.currentThread().getName() + " " + System.currentTimeMillis());

        Employee employee = utils.newEmploe();
        Car car = utils.newCar();

        try {
            repository.insertFromCallableOBS(employee).subscribe(
                    new Consumer<Long>() {
                        @Override
                        public void accept(Long aLong) throws Exception {
                            Log.d(this.getClass().getSimpleName(), " " + new Throwable().getStackTrace()[0].getMethodName() + "  " + Thread.currentThread().getName() + " " + System.currentTimeMillis());
                            Log.d(this.getClass().getSimpleName(), "insertFromCallableOBS aLong " + aLong);
                            employee.setId(aLong);
                            RepositoryExecutor repositoryExecutor = new RepositoryExecutor();
                            car.setEmployeeId(aLong);
                            repositoryExecutor.InsertExecutor(car);
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            repository.getUserName(employee.getName()+"1")
                     .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())

                    .subscribe(employee1 -> {
                        Log.e("getUserName", "Flowable");
                    Log.e("getUserName",employee1.toString());
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Observable<Long> ooo = repository.insertFromCallableOBS(employee).subscribeOn(new Observable<Long>());


        //Log.e(this.getClass().getSimpleName(), employee.toString());


        Log.d(this.getClass().getSimpleName(), " " + new Throwable().getStackTrace()[0].getMethodName() + "  " + Thread.currentThread().getName() + " " + System.currentTimeMillis());

    }

    public void InsertExecutorClass(Employee employee, Car car){

        RepositoryExecutor repositoryExecutor = new RepositoryExecutor();

        Log.e(this.getClass().getSimpleName(), employee.toString());
        Log.e(this.getClass().getSimpleName(), car.toString());

        try {
            repositoryExecutor.InsertExecutor(employee);
            repositoryExecutor.InsertExecutor(car);
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    private  void InUI(Employee employee, Car car){

        Log.e(this.getClass().getSimpleName(), employee.toString());
        Log.e(this.getClass().getSimpleName(), car.toString());

         RepositoryInUI repositoryInUI = new RepositoryInUI();
        try {
            employee.setId(repositoryInUI.InsertId(employee));
            car.setEmployeeId(employee);
            repositoryInUI.Insert(car);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}