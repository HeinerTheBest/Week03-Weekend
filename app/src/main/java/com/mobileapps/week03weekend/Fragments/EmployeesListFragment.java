package com.mobileapps.week03weekend.Fragments;

import android.content.Context;
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
import com.mobileapps.week03weekend.Models.Employee;
import com.mobileapps.week03weekend.R;
import com.mobileapps.week03weekend.Threadings.GetAllEmployeeByCategoryThread;

import java.util.ArrayList;

public class EmployeesListFragment extends Fragment implements GetAllEmployeeByCategoryThread.GetAllEmployeeByCategoryCallBack
{
    RecyclerView recyclerView;

    MainActivity mainActivity;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_employees_list, container, false);



        mainActivity = ((MainActivity)getActivity());
        mainActivity.setBack(true);

        Log.d("Heiner",mainActivity.getDataFilter());
        Log.d("Heiner",""+mainActivity.getOpcFilter());
        GetAllEmployeeByCategoryThread getAllEmployeeByCategoryThread = new GetAllEmployeeByCategoryThread(this,mainActivity.getOpcFilter(),mainActivity.getDataFilter(),getContext(),mainActivity,v);
        Thread thread = new Thread(getAllEmployeeByCategoryThread);
        thread.start();




        return v;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle("Employee ");
    }

    @Override
    public void returnEmployeesList(EmployeeAdapter employeeAdapter, Context thisContext,View view)
    {
        recyclerView = view.findViewById(R.id.rvEmployeesLists);
        if(recyclerView != null) {
            recyclerView.setAdapter(employeeAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(thisContext));
        }
    }
}
