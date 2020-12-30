package com.example.employeeroom.ui.activity;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.employeeroom.db.model.Car;
import com.example.employeeroom.db.model.Employee;
import com.example.employeeroom.repo.RepositotyLiveData;

import java.util.List;

public class MainActivityViewModel extends AndroidViewModel {

    private RepositotyLiveData repositotyLiveData;

    private LiveData<List<Employee>> mAllEmployee;

    private LiveData<List<Car>> mAllCar;

    public MainActivityViewModel(Application application) {
        super(application);
        repositotyLiveData = new RepositotyLiveData(application);
        mAllEmployee = repositotyLiveData.gelAllEmployee();
        mAllCar = repositotyLiveData.getAllCar();

    }

    LiveData<List<Employee>> getAllWords() { return mAllEmployee; }

    LiveData<List<Car>> getAllCar(){return mAllCar;}


    // https://google-developer-training.github.io/android-developer-advanced-course-practicals/unit-6-working-with-architecture-components/lesson-14-room,-livedata,-viewmodel/14-1-a-room-livedata-viewmodel/14-1-a-room-livedata-viewmodel.html





}

