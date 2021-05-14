package com.example.employeeroom.ui.activity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.employeeroom.R;
import com.example.employeeroom.db.model.Employee;

import java.util.LinkedList;
import java.util.List;

public class EmployeeAdapter  extends RecyclerView.Adapter<EmployeeAdapter.ViewHolder> {

    private List<Employee> employeeList = new LinkedList<>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_employee,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(employeeList.get(position));

    }

    @Override
    public int getItemCount() {
        if (employeeList!=null){return employeeList.size();}else {return 0;}
    }

    public void setData(List<Employee> employeeList){this.employeeList=employeeList;}

    public List<Employee> getData(){return employeeList;}

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView id;
        TextView name;
        TextView salary;
        TextView timeSt;
        TextView timeInt;
        TextView timeNUM;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.tv_EmployeeID);
            name = itemView.findViewById(R.id.tv_EmployeeName);
            salary = itemView.findViewById(R.id.tv_EmployeeSalary);
            timeSt = itemView.findViewById(R.id.tv_EmployeeTimeSt);
            timeInt = itemView.findViewById(R.id.tv_EmployeeTimeInt);
            timeNUM = itemView.findViewById(R.id.tv_EmployeeTimeNUM);

        }

        public void bind(Employee employee) {
            id.setText(Long.toString(employee.getId()));
            name.setText(employee.getName());
            salary.setText(Integer.toString(employee.getSalary()));
            try {
                timeInt.setText(Integer.toString(employee.getTimeEntryNumber()));
                timeNUM.setText(Long.toString(employee.getTimeEntryNUMERIC()));
            } catch (Exception e) {
                e.printStackTrace();
            }

            timeSt.setText(employee.getTimeEntrySt());

        }
    }
}
