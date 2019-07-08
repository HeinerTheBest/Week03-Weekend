package com.mobileapps.week03weekend.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.mobileapps.week03weekend.Activities.MainActivity;
import com.mobileapps.week03weekend.Fragments.EmployeeOperationFragment;
import com.mobileapps.week03weekend.Models.Employee;
import com.mobileapps.week03weekend.R;

import java.util.ArrayList;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.ViewHolder>
{
    ArrayList<Employee> employees;
    Context context;
    MainActivity mainActivity;

    public EmployeeAdapter(ArrayList<Employee> employees, Context context, MainActivity mainActivity) {
        this.employees = employees;
        this.context = context;
        this.mainActivity = mainActivity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.employee_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        String name = employees.get(position).getFirstName()+" "+employees.get(position).getLastName();
        holder.tvTitle.setText(name);
        holder.tvPosition.setText(employees.get(position).getPosition());
        holder.tvDepartment.setText(employees.get(position).getDepartment());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivity.setEmployeeId(employees.get(position).getId());
                mainActivity.setEmployeeConfigurationOption(MainActivity.KEY_OPERATION_CHECK);
                Fragment fragment = new EmployeeOperationFragment();
                mainActivity.startFragment(fragment);
            }
        });
    }

    @Override
    public int getItemCount() {
        return employees.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView tvTitle;
        TextView tvPosition;
        TextView tvDepartment;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle      = itemView.findViewById(R.id.tvName);
            tvPosition   = itemView.findViewById(R.id.tvPosition);
            tvDepartment = itemView.findViewById(R.id.tvDepartment);
        }
    }
}
