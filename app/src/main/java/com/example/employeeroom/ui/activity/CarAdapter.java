package com.example.employeeroom.ui.activity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.employeeroom.R;
import com.example.employeeroom.db.model.Car;

import java.util.LinkedList;
import java.util.List;

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.ViewHolder>{

    private List<Car> carList = new LinkedList<>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_car,parent,false);
        return new CarAdapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(carList.get(position));
    }

    @Override
    public int getItemCount() {
        if (carList!=null){return carList.size();}else {return 0;}
    }

    public void setData(List<Car> carList){this.carList=carList;}

    public List<Car> getData(){return carList;}


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView id;
        TextView madel;
        TextView year;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.tv_CarID);
            madel = itemView.findViewById(R.id.tv_EmployeeName);
            year = itemView.findViewById(R.id.tv_CarYaer);
        }

        public void bind(Car car) {
            id.setText(Long.toString(car.getId()));
            madel.setText(car.getModel());
            year.setText(Integer.toString(car.getYaer()));
        }

    }
}
