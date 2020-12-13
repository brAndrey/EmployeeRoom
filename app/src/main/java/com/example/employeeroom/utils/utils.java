package com.example.employeeroom.utils;

import android.util.Log;

import com.example.employeeroom.db.model.Employee;

import java.util.List;

public class utils {

    public static Employee newEmploe() {


        String time = String.valueOf(System.currentTimeMillis());

        Employee employee = new Employee(
                "John_" + time.substring(time.length() - 3)
                , 10000
        );

        return employee;
    }

    public static void PrintList(List<Employee> dataList) {
        if (dataList.size() > 0) {
            for (Employee elem : dataList) {
                Log.i("PrintList", elem.toString());
            }
        }

    }
}
