package com.mobileapps.week03weekend.Fragments;

import android.content.Context;
import android.net.Uri;
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
import com.mobileapps.week03weekend.Models.Filter;
import com.mobileapps.week03weekend.R;
import com.mobileapps.week03weekend.Threadings.getTheItemForTheFIlterThread;

import java.util.ArrayList;
import java.util.HashSet;


public class FilterFragment extends Fragment implements getTheItemForTheFIlterThread.ThreadCallBack {
    ArrayList<Filter> filters = new Filter().getAllTheFilter();
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment_filter, container, false);
        ((MainActivity)getActivity()).setBack(false);
        for (int i=0;i<=4;i++)
        {
            getTheItemForTheFIlterThread getTheItemForTheFIlterThread = new getTheItemForTheFIlterThread(i,v.getContext(),this);
            Thread thread = new Thread(getTheItemForTheFIlterThread);
            thread.start();
            Log.d("Heiner","Finish for nro "+i);
        }

        recyclerView = v.findViewById(R.id.rvFilter);
        FilterAdapter filterAdapter = new FilterAdapter(filters,v.getContext());
        recyclerView.setAdapter(filterAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(v.getContext()));


        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Filter");
    }

    @Override
    public void returnOpc(HashSet<String> opc) {
        for (int i=0;i<=4;i++)
        {
            filters.get(i).setOpc(opc);
        }
    }
}
