package com.example.employeeroom.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

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

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class MainActivity extends AppCompatActivity {

    Repository repository;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        repository = new Repository();
    }

    public void onInsertProcess(View view) {

        Log.d(this.getClass().getSimpleName(), " " + new Throwable().getStackTrace()[0].getMethodName() + "  " + Thread.currentThread().getName() + " " + System.currentTimeMillis());

        Employee employee = utils.newEmploe();
        Car car = utils.newCar();

        try {
            Disposable taskObservable = Observable
                    .just(repository.insertFromCallable(employee))
                    //.takeWhile(aLong -> aLong!=0)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(aLong -> {
//                    car.setEmployeeId(aLong);
                                Log.e(this.getClass().getSimpleName(), "Disposable aLong " + aLong);
                                //Log.e(this.getClass().getSimpleName(), car.toString());
                            }                    );
            //repository.insertFromCallable(employee));
            //  car.setEmployeeId(employee);

        } catch (Exception e) {
            e.printStackTrace();
        }

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