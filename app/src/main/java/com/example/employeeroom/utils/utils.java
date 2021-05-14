package com.example.employeeroom.utils;

import android.util.Log;

import com.example.employeeroom.db.model.Car;
import com.example.employeeroom.db.model.Employee;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class utils {

    public static Employee newEmploe() {


        String time = String.valueOf(System.currentTimeMillis());

        Employee employee = new Employee(
                "John_" + time.substring(time.length() - 1)
                , 10000
        );

        employee.setTimeEntryNumber( (int) System.currentTimeMillis());
        employee.setTimeEntryNUMERIC(System.currentTimeMillis());
        employee.setTimeEntrySt(time);

        return employee;
    }



    public static void PrintList(List<Employee> dataList) {
        Log.d("PrintList", " "+ new Throwable().getStackTrace()[0].getMethodName() +"  "+ Thread.currentThread().getName()+" "+System.currentTimeMillis());
        if (dataList.size() > 0) {
            for (Employee elem : dataList) {
                Log.i("PrintList", elem.toString());
            }
        }

    }


    public  static Car newCar(){
        String time = String.valueOf(System.currentTimeMillis());
        return new Car("Ваз",2009);

    }

    public static void PrintCar(List<Car> dataList){
        Log.d("PrintCar", " "+ new Throwable().getStackTrace()[0].getMethodName() +"  "+ Thread.currentThread().getName()+" "+System.currentTimeMillis());
        if (dataList.size() > 0) {
            for (Car elem : dataList) {
                Log.i("PrintList", elem.toString());
            }
        }
    }

    public static String TimeFormat() {
        long yourmilliseconds = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd,yyyy HH:mm");
        Date resultdate = new Date(yourmilliseconds);
        return sdf.format(resultdate);
    }

    public static long ReadStringToDataFormat(String dataInt) {
        //Log.e("WriteDataFormat","int - " + dataInt);
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("MMM dd,yyyy HH:mm", Locale.getDefault());
            Date resultdate = sdf.parse(dataInt, new ParsePosition(0));
            //  Log.e("WriteDataFormat"," "+resultdate.getTime());
            return resultdate.getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
