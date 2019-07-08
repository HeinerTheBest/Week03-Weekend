package com.mobileapps.week03weekend.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mobileapps.week03weekend.Activities.MainActivity;
import com.mobileapps.week03weekend.Adapters.FilterAdapter;
import com.mobileapps.week03weekend.DataBase.EmployeeDataBaseHelper;
import com.mobileapps.week03weekend.Models.Filter;
import com.mobileapps.week03weekend.R;
import com.mobileapps.week03weekend.Threadings.getTheItemForTheFIlterThread;

import java.util.ArrayList;
import java.util.HashSet;


public class FilterFragment extends Fragment  {
    ArrayList<Filter> filters = new Filter().getAllTheFilter();
    RecyclerView recyclerView;
    Context context;
    final static String TAG = "FilterFragment";
    MainActivity mainActivity;
    View v;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        v = inflater.inflate(R.layout.fragment_filter, container, false);
        mainActivity = ((MainActivity)getActivity());
        mainActivity.setBack(false);

        //getTheItemForTheFIlterThread getTheItemForTheFIlterThread = new getTheItemForTheFIlterThread(v.getContext(),this,mainActivity,v);
       // Thread thread = new Thread(getTheItemForTheFIlterThread);
        //thread.start();

        context = v.getContext();

        getItemForFilter();

        return v;

    }

    public void getItemForFilter()
    {
        class GetItemForFilterTask extends AsyncTask<String,String,ArrayList<Filter>>
        {

            @Override
            protected ArrayList<Filter> doInBackground(String... strings) {
                ArrayList<Filter> filters = new Filter().getAllTheFilter();
                EmployeeDataBaseHelper employeeDataBaseHelper = new EmployeeDataBaseHelper(context);
                filters.get(0).setOpc(employeeDataBaseHelper.getAllCity());
                filters.get(1).setOpc(employeeDataBaseHelper.getAllState());
                filters.get(2).setOpc(employeeDataBaseHelper.getAllZipCode());
                filters.get(3).setOpc(employeeDataBaseHelper.getAllPosition());
                filters.get(4).setOpc(employeeDataBaseHelper.getAllDepartment());
                return filters;
            }

            @Override
            protected void onPostExecute(ArrayList<Filter> filters) {
                super.onPostExecute(filters);
                FilterAdapter filterAdapter = new FilterAdapter(filters, context, mainActivity);
                recyclerView = v.findViewById(R.id.rvFilter);
                if(recyclerView != null) {
                    recyclerView.setAdapter(filterAdapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(context));
                }
            }
        }

        GetItemForFilterTask getItemForFilterTask = new GetItemForFilterTask();
        getItemForFilterTask.execute();
    }


    public static String getTAG() {
        return TAG;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Filter");
    }

    /*@Override
    public void returnOpc(FilterAdapter filterAdapter,Context thisContext,View view)
    {
        recyclerView = view.findViewById(R.id.rvFilter);

        if(recyclerView != null) {
            recyclerView.setAdapter(filterAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(thisContext));
        }
    }*/




}
