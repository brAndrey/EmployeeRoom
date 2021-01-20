package com.example.employeeroom.repo;

import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.employeeroom.db.App;
import com.example.employeeroom.db.AppDataBase;
import com.example.employeeroom.db.EmployeeDao;
import com.example.employeeroom.db.model.Car;
import com.example.employeeroom.db.model.Employee;
import com.example.employeeroom.utils.utils;

import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class RepositoryGet {


// *************************************************************************

    class CallableLongAction implements Callable<List<Employee>> {

        @Override
        public List<Employee> call() throws Exception {
            Log.d(this.getClass().getSimpleName(), " " + new Throwable().getStackTrace()[0].getMethodName() + "  " + Thread.currentThread().getName() + " " + System.currentTimeMillis());
            AppDataBase appDataBase = App.getInstance().getDatabase();
            return appDataBase.employeeDao().getAll();
        }
    }

    public void getFromCallable() {

        Observable.fromCallable(new CallableLongAction())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Employee>>() {
                    @Override
                    public void accept(List<Employee> employees) throws Exception {
                        utils.PrintList(employees);
                    }
                });
    }

    //**************************************************************************

    class CallableLongActionCar implements Callable<List<Car>> {

        @Override
        public List<Car> call() throws Exception {
            Log.d(this.getClass().getSimpleName(), " " + new Throwable().getStackTrace()[0].getMethodName() + "  " + Thread.currentThread().getName() + " " + System.currentTimeMillis());
            AppDataBase appDataBase = App.getInstance().getDatabase();
            return appDataBase.carDao().getAll();
        }
    }

    public void getFromCallableCar() {

        Observable.fromCallable(new CallableLongActionCar())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Car>>() {
                    @Override
                    public void accept(List<Car> cars) throws Exception {
                        utils.PrintCar(cars);
                    }
                });
    }
    //**************************************************************************

}
