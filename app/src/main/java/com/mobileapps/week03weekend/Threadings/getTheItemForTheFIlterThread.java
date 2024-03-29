package com.mobileapps.week03weekend.Threadings;

import android.content.Context;
import android.view.View;

import com.mobileapps.week03weekend.Activities.MainActivity;
import com.mobileapps.week03weekend.Adapters.FilterAdapter;
import com.mobileapps.week03weekend.DataBase.EmployeeDataBaseHelper;
import com.mobileapps.week03weekend.Models.Filter;

import java.util.ArrayList;

public class getTheItemForTheFIlterThread implements Runnable
{

    Context context;
    ThreadCallBack callBack;
    MainActivity mainActivity;
    View view;

    public getTheItemForTheFIlterThread(Context context, ThreadCallBack callBack, MainActivity mainActivity,View view) {
        this.context = context;
        this.callBack = callBack;
        this.mainActivity = mainActivity;
        this.view = view;
    }

    @Override
    public void run()
    {
        ArrayList<Filter> filters = new Filter().getAllTheFilter();
        EmployeeDataBaseHelper employeeDataBaseHelper = new EmployeeDataBaseHelper(context);
        filters.get(0).setOpc(employeeDataBaseHelper.getAllCity());
        filters.get(1).setOpc(employeeDataBaseHelper.getAllState());
        filters.get(2).setOpc(employeeDataBaseHelper.getAllZipCode());
        filters.get(3).setOpc(employeeDataBaseHelper.getAllPosition());
        filters.get(4).setOpc(employeeDataBaseHelper.getAllDepartment());
        FilterAdapter filterAdapter = new FilterAdapter(filters, context, mainActivity);
        callBack.returnOpc(filterAdapter,context,view);
    }

    public interface ThreadCallBack
    {
        void returnOpc(FilterAdapter filterAdapter,Context context,View view);
    }

}
