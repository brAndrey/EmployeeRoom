package com.example.employeeroom.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.employeeroom.db.model.Car;

import java.util.List;
@Dao
public interface CarDao {
    @Query("SELECT *FROM car")
    List<Car> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Car car);

    @Update
    void update(Car car);

    @Delete
    void delete(Car car);

}
