package com.example.employeeroom.repository;

import android.util.Log;

import com.example.employeeroom.db.App;
import com.example.employeeroom.db.AppDataBase;
import com.example.employeeroom.db.model.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class Repository {

    private Object Future;

    public void insert (Employee employee){

    }

    //********************************************************************

    class CallableInsertEmployee implements Callable<Long>{
        private final Employee employee;

        public CallableInsertEmployee(Employee employee){
            this.employee=employee;
        }

        @Override
        public Long call() throws Exception {
            AppDataBase appDataBase = App.getInstance().getDatabase();
            return appDataBase.employeeDao().insertId(employee);
        }
    }

    public Observable insertFromCallableOBS(Employee employee ) {
     return   Observable.fromCallable( new CallableInsertEmployee(employee))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    //********************************************************************


    public Flowable<Employee> getUserName(String name) {
        AppDataBase appDataBase = App.getInstance().getDatabase();
        return appDataBase.employeeDao().getByNameF(name);
    }

    public Observable<Employee> getUserNameObs(String name) {
        AppDataBase appDataBase = App.getInstance().getDatabase();
        return appDataBase.employeeDao().getByName(name);
    }

    public Flowable<List<Employee>> getUserNameList(String name) {
        AppDataBase appDataBase = App.getInstance().getDatabase();
        return appDataBase.employeeDao().getByNameFList(name);
    }
    public Observable<List<Employee>> getUserNameObsList(String name) {
        AppDataBase appDataBase = App.getInstance().getDatabase();
        return appDataBase.employeeDao().getByNameList(name);
    }
    //********************************************************************

    public void ClearBase(){
        AppDataBase appDataBase = App.getInstance().getDatabase();
        appDataBase.employeeDao().deleteEmployees();
    }





// Observable.fromCallable(() -> { sharedPreferences.edit() .putString(DB_COMPANY, LoganSquare.serialize( CompanyDBTransformation.get(user.getCompany()) )).apply();
// return user; })
//            .onErrorResumeNext( throwable -> Observable.error(new CompanySerializationException(throwable)) );


}
