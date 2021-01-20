package com.example.employeeroom.over;

import android.util.Log;

import com.example.employeeroom.db.App;
import com.example.employeeroom.db.AppDataBase;
import com.example.employeeroom.db.model.Employee;
import com.example.employeeroom.repository.Repository;
import com.example.employeeroom.utils.utils;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.io.IOException;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class RepositoryNotWork {

    //****************************************************************************


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

    public void getEmployeeThroughName(Employee employee) {
        Repository repository = new Repository();
        try {
            repository.getUserNameList(employee.getName())
                    //.repeat(1)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<List<Employee>>() {
                        @Override
                        public void onSubscribe(Subscription s) {
                            Log.e("Flowable ", " onSubscribe " + employee.getName());
                        }

                        @Override
                        public void onNext(List<Employee> employees) {
                            Log.e("Flowable ", " onNext " + employee.getName());
                        }

                        @Override
                        public void onError(Throwable t) {
                            Log.e("Flowable ", " onError " + employee.getName());
                        }

                        @Override
                        public void onComplete() {
                            Log.e("Flowable ", " onComplete " + employee.getName());
                        }
                    });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
