package com.example.employeeroom.repository;

import android.util.Log;

import com.example.employeeroom.db.App;
import com.example.employeeroom.db.AppDataBase;
import com.example.employeeroom.db.model.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

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
    public Long insertFromCallable(Employee employee ) {

        final List<Long> resultId = new ArrayList<>();
        long[] longs = new long[1];

        //Observable.fromCallable(new Callable<File>() { @Override public File call() throws Exception { return downloadFileFromNetwork(); } }

      Observable.fromCallable( new CallableInsertEmployee(employee))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        Log.d(this.getClass().getSimpleName(), " " + new Throwable().getStackTrace()[0].getMethodName() + "  " + Thread.currentThread().getName() + " " + System.currentTimeMillis());
                        resultId.add(aLong);
                        longs[0] = aLong;

                        Log.d("insertFromCallable "+this.getClass().getSimpleName(), "aLong " + aLong);
                    } });


        Log.d(this.getClass().getSimpleName(), " "+ new Throwable().getStackTrace()[0].getMethodName() +"  "+ Thread.currentThread().getName()+" "+System.currentTimeMillis());
        Log.d(this.getClass().getSimpleName(), "longs[0] "+longs[0]);

        return  longs[0];
    }

    public Observable insertFromCallableOBS(Employee employee ) {

        final List<Long> resultId = new ArrayList<>();

     return   Observable.fromCallable( new CallableInsertEmployee(employee))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());


    }



// Observable.fromCallable(() -> { sharedPreferences.edit() .putString(DB_COMPANY, LoganSquare.serialize( CompanyDBTransformation.get(user.getCompany()) )).apply();
// return user; })
//            .onErrorResumeNext( throwable -> Observable.error(new CompanySerializationException(throwable)) );


}
