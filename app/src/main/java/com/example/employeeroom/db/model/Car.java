package com.example.employeeroom.db.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(foreignKeys = @ForeignKey(entity = Employee.class,
        parentColumns = "id",
        childColumns = "employee_id",
        onDelete = CASCADE))



//@Entity
public class Car {

    @PrimaryKey(autoGenerate = true)
    public long id;

    public String model;

    public int yaer;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYaer() {
        return yaer;
    }

    public void setYaer(int yaer) {
        this.yaer = yaer;
    }

    @ColumnInfo(name = "employee_id")
    public long employeeId;

    public long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }

    public void setEmployeeId(Employee employee) {
        this.employeeId = employee.getId();
    }


//    @Ignore
//    @NonNull
//    public Employee employee;

    public Car(String model, int yaer) {

        this.model = model;
        this.yaer = yaer;
        //this.employeeId = employee.getId();

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n Car");
        sb.append("\n id:" + this.id);
        sb.append("\n model:" + this.model);
        sb.append("\n yaer:" + this.yaer);
        sb.append("\n employeeId:" + this.employeeId);
        sb.append("\n *****************");
        return sb.toString();
    }
}
