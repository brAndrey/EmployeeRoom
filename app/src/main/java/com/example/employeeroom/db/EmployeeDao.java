package com.example.employeeroom.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.PrimaryKey;
import androidx.room.Query;
import androidx.room.Update;

import com.example.employeeroom.db.model.Employee;

import java.util.List;

@Dao
public interface EmployeeDao {

    @Query("SELECT *FROM employee")
    List<Employee> getAll();

    @Query("SELECT *FROM employee WHERE id= :id")
    Employee getById(long id);

    @Insert
    void insert(Employee employee);

    @Update
    void update(Employee employee);

    @Delete
    void delete(Employee employee);

}
