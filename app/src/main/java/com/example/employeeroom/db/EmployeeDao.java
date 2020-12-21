package com.example.employeeroom.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.PrimaryKey;
import androidx.room.Query;
import androidx.room.Update;

import com.example.employeeroom.db.model.Employee;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;

@Dao
public interface EmployeeDao {

    @Query("SELECT *FROM employee")
    List<Employee> getAll();

    @Query("SELECT * FROM employee")
    LiveData<List<Employee>> getEmployee();

//    @Query("SELECT * FROM employee")
//    Flowable<List<Employee>> getCoupons();


    @Query("SELECT *FROM employee WHERE id= :id")
    Employee getById(long id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Employee employee);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insertId(Employee employee);

    @Update
    void update(Employee employee);

    @Delete
    void delete(Employee employee);

}
