package com.example.employeeroom.ui.activity;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.employeeroom.db.model.Car;
import com.example.employeeroom.db.model.Employee;
import com.example.employeeroom.repo.RepositoryExecutor;
import com.example.employeeroom.repo.RepositoryInUI;
import com.example.employeeroom.repo.RepositotyLiveData;
import com.example.employeeroom.repository.Repository;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainActivityViewModel extends AndroidViewModel {

    private RepositotyLiveData repositotyLiveData;

    private LiveData<List<Employee>> mAllEmployee;

    private LiveData<List<Car>> mAllCar;

    private CompositeDisposable disposables = new CompositeDisposable();

    public MainActivityViewModel(Application application) {
        super(application);
        repositotyLiveData = new RepositotyLiveData(application);
        mAllEmployee = repositotyLiveData.gelAllEmployee();
        mAllCar = repositotyLiveData.getAllCar();
    }

    LiveData<List<Employee>> getAllWords() {
        return mAllEmployee;
    }

    LiveData<List<Car>> getAllCar() {
        return mAllCar;
    }

    public void InsertEmployee(Employee employee) {
     //   disposables.clear();
        Repository repository = new Repository();

        Log.e(this.getClass().getSimpleName(), employee.toString());
        try {
            repository.getUserNameObs(employee.getName())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<Employee>() {
                                   @Override
                                   public void onSubscribe(Disposable d) {
                                       disposables.add(d);
                                       Log.e("IsertEmployee", " onSubscribe " + employee.getName());
                                   }

                                   @Override
                                   public void onNext(Employee employee) {
                                       Log.e("IsertEmployee", " onNext " + employee.getName()+" "+employee.getId());
                                   }

                                   @Override
                                   public void onError(Throwable e) {
                                       Log.e("IsertEmployee", " onError " + employee.getName());
                                   }

                                   @Override
                                   public void onComplete() {
                                       Log.e("IsertEmployee", " onComplete " + employee.getName());
                                   }
                               }

                    );

        } catch (Exception e) {
            e.printStackTrace();
        }

        try{
            repository.getUserNameObsList(employee.getName())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                    new Observer<List<Employee>>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                            disposables.add(d);
                            Log.e("InsertEmployeeList", " onSubscribe " + employee.getName());
                        }

                        @Override
                        public void onNext(List<Employee> employees) {
                            Log.e("InsertEmployeeList", " onNext " + employee.getName()+" "+employees.size());
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.e("InsertEmployeeList", " onError " + employee.getName());
                        }

                        @Override
                        public void onComplete() {
                            Log.e("InsertEmployeeList", " onComplete " + employee.getName());
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // https://google-developer-training.github.io/android-developer-advanced-course-practicals/unit-6-working-with-architecture-components/lesson-14-room,-livedata,-viewmodel/14-1-a-room-livedata-viewmodel/14-1-a-room-livedata-viewmodel.html

    public void InsertFromCallableOBS(Employee employee, Car car) {
        try {
            Repository repository = new Repository();
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
    }

    public void InsertExecutorClass(Employee employee, Car car) {

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

    public void ClearBase(){
        Repository repository = new Repository();
        repository.ClearBase();
    }

    private void InUI(Employee employee, Car car) {
        //класс в котором внесение данных производится не через RX а через UI

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

