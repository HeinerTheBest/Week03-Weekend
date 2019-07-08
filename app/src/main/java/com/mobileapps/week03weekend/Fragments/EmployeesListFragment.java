package com.mobileapps.week03weekend.Fragments;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mobileapps.week03weekend.Activities.MainActivity;
import com.mobileapps.week03weekend.Adapters.EmployeeAdapter;
import com.mobileapps.week03weekend.DataBase.EmployeeDataBaseHelper;
import com.mobileapps.week03weekend.Models.Employee;
import com.mobileapps.week03weekend.R;
import com.mobileapps.week03weekend.Threadings.GetAllEmployeeByCategoryThread;

import java.util.ArrayList;

public class EmployeesListFragment extends Fragment 
{
    RecyclerView recyclerView;
    MainActivity mainActivity;
    Context context;
    View v;
    
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         v = inflater.inflate(R.layout.fragment_employees_list, container, false);
        context = getContext();


        mainActivity = ((MainActivity)getActivity());
        mainActivity.setBack(true);

        Log.d("Heiner",mainActivity.getDataFilter());
        Log.d("Heiner",""+mainActivity.getOpcFilter());
        //GetAllEmployeeByCategoryThread getAllEmployeeByCategoryThread = new GetAllEmployeeByCategoryThread(this,mainActivity.getOpcFilter(),mainActivity.getDataFilter(),getContext(),mainActivity,v);
        //Thread thread = new Thread(getAllEmployeeByCategoryThread);
        //thread.start();



        getRecyclerView();
        return v;
    }

    private void getRecyclerView() {

        class GetAllEmployeeByCategoreeTask extends AsyncTask<String, String, ArrayList<Employee>> {
            @Override
            protected ArrayList<Employee> doInBackground(String... strings) {
                ArrayList<Employee> employees = new ArrayList<>();
                EmployeeDataBaseHelper employeeDataBaseHelper = new EmployeeDataBaseHelper(context);
                switch (mainActivity.getOpcFilter()) {
                    case 0:
                        employees = employeeDataBaseHelper.getEmployeeByCity(mainActivity.getDataFilter());
                        break;
                    case 1:
                        employees = employeeDataBaseHelper.getEmployeeByState(mainActivity.getDataFilter());
                        break;
                    case 2:
                        employees = employeeDataBaseHelper.getEmployeeByZipCode(mainActivity.getDataFilter());
                        break;
                    case 3:
                        employees = employeeDataBaseHelper.getEmployeeByPosition(mainActivity.getDataFilter());
                        break;
                    case 4:
                        employees = employeeDataBaseHelper.getEmployeeByDepartment(mainActivity.getDataFilter());
                        break;
                }
                return employees;
            }

            @Override
            protected void onPostExecute(ArrayList<Employee> employees) {
                super.onPostExecute(employees);
                EmployeeAdapter employeeAdapter = new EmployeeAdapter(employees, context, mainActivity);
                recyclerView = v.findViewById(R.id.rvEmployeesLists);
                if (recyclerView != null) {
                    recyclerView.setAdapter(employeeAdapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(context));
                }
            }
        }
        GetAllEmployeeByCategoreeTask getAllEmployeeByCategoreeTask = new GetAllEmployeeByCategoreeTask();
        getAllEmployeeByCategoreeTask.execute();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle("Employee ");
    }

    /*@Override
    public void returnEmployeesList(EmployeeAdapter employeeAdapter, Context thisContext,View view)
    {
        recyclerView = view.findViewById(R.id.rvEmployeesLists);
        if(recyclerView != null) {
            recyclerView.setAdapter(employeeAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(thisContext));
        }
    }*/
    
    
}
