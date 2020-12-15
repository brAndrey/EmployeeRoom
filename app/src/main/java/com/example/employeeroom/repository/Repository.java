package com.example.employeeroom.repository;

import android.util.Log;

import com.example.employeeroom.db.App;
import com.example.employeeroom.db.AppDataBase;
import com.example.employeeroom.db.model.Employee;
import com.example.employeeroom.utils.utils;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class Repository {

    public void insert (Employee employee){

    }

    public void InsertRX(Employee employee) {

        // https://stackoverflow.com/questions/46482423/android-room-asynctask

//        executor.execute(() -> {
//            appDataBase.employeeDao().insert(employee);
//        });

        AppDataBase appDataBase = App.getInstance().getDatabase();

        Executor    executor = Executors.newSingleThreadExecutor();

        executor.execute(new Runnable() {
            @Override
            public void run() {
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                Log.d(this.getClass().getSimpleName(), " "+ new Throwable().getStackTrace()[0].getMethodName() +"  "+ Thread.currentThread().getName()+" "+System.currentTimeMillis());
                appDataBase.employeeDao().insert(employee);
            }
        });
    }


    // \/ не работает т.к. в потоке UI
    public void getCoupons() {

        Log.d(this.getClass().getSimpleName(), " "+ new Throwable().getStackTrace()[0].getMethodName() +"  "+ Thread.currentThread().getName()+" "+System.currentTimeMillis());

        try {
            Observable.just( longAction())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<List<Employee>>() {
                        @Override
                        public void accept(final List<Employee> employees) throws Exception {
                            Log.d(this.getClass().getSimpleName(), " "+ new Throwable().getStackTrace()[0].getMethodName() +"  "+ Thread.currentThread().getName()+" "+System.currentTimeMillis());
                            utils.PrintList(employees);
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private List<Employee> longAction() throws IOException {

        Log.d(this.getClass().getSimpleName(), " "+ new Throwable().getStackTrace()[0].getMethodName() +"  "+ Thread.currentThread().getName()+" "+System.currentTimeMillis());
        AppDataBase appDataBase = App.getInstance().getDatabase();

        return appDataBase.employeeDao().getAll();
    }
//  /\ не работает т.к. в потоке UI

    class CallableLongAction implements Callable<List<Employee> >{

        @Override
        public List<Employee> call() throws Exception {
            Log.d(this.getClass().getSimpleName(), " "+ new Throwable().getStackTrace()[0].getMethodName() +"  "+ Thread.currentThread().getName()+" "+System.currentTimeMillis());
            AppDataBase appDataBase = App.getInstance().getDatabase();
            return appDataBase.employeeDao().getAll();
        }
    }

    public void getFromCallable( ) {

        Observable.fromCallable( new CallableLongAction())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Employee>>() {
                    @Override
                    public void accept(List<Employee> employees) throws Exception {
                        utils.PrintList(employees);
                    }
                });
    }

}
