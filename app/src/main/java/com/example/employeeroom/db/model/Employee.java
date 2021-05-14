package com.example.employeeroom.db.model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Employee {

    @PrimaryKey(autoGenerate = true)
    private long id;

    private String name;

    private int salary;

    private String timeEntrySt;

    private int timeEntryNumber;

    private long timeEntryNUMERIC;

    public Employee(String name, int salary){

        this.name=name;
        this.salary=salary;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getTimeEntrySt() { return timeEntrySt;}

    public void setTimeEntrySt(String timeEntrySt) { this.timeEntrySt = timeEntrySt;}

    public int getTimeEntryNumber() {return timeEntryNumber;}

    public void setTimeEntryNumber(int timeEntryNumber) {this.timeEntryNumber = timeEntryNumber;}

    public long getTimeEntryNUMERIC() {
        return timeEntryNUMERIC;
    }

    public void setTimeEntryNUMERIC(long timeEntryNUMERIC) {
        this.timeEntryNUMERIC = timeEntryNUMERIC;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("\n Employee");
        sb.append("\n id:" + this.id);
        sb.append("\n name:" + this.name);
        sb.append("\n salary:" + this.salary);
        sb.append("\n timeEntrySt  :" + this.timeEntrySt);
        sb.append("\n timeEntryNumber :" + this.timeEntryNumber);
        sb.append("\n timeEntryNUMERIC:" + this.timeEntryNUMERIC);

        sb.append("\n *****************");
        return sb.toString();
    }
}
