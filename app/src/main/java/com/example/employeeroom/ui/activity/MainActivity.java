package com.example.employeeroom.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.employeeroom.R;
import com.example.employeeroom.db.App;
import com.example.employeeroom.db.AppDataBase;
import com.example.employeeroom.db.model.Employee;
import com.example.employeeroom.utils.utils;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    AppDataBase appDataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        appDataBase = App.getInstance().getDatabase();

    }

    public void onStartProcess(View view) {
        Employee employee = utils.newEmploe();
        Log.e (this.getClass().getSimpleName(),employee.toString());
        appDataBase.employeeDao().insert(employee);

        utils.PrintList(appDataBase.employeeDao().getAll());


    }

    public void InsertRX(Employee employee){


        //https://www.zoftino.com/android-persistency-room-rxjava

        
//   Observable.fromCallable(()->appDataBase.employeeDao().insert(employee))
//        .subscribeOn(Schedulers.io())
//        .subscribe();



//                .fromCallable {
//            Runnable {
//                appDb.blogDao().insert(blog)
//            }.run()
//        }
//            .subscribeOn(Schedulers.io())
//                .subscribe {
//            D.showSnackMsg(context as Activity, R.string.book_mark_msg)
//        }
    }
}