package com.mobileapps.week03weekend.Threadings;

import android.content.Context;
import com.mobileapps.week03weekend.DataBase.EmployeeDataBaseHelper;
import com.mobileapps.week03weekend.Models.Filter;

import java.util.ArrayList;

public class getTheItemForTheFIlterThread implements Runnable
{

    Context context;
    ThreadCallBack callBack;

    public getTheItemForTheFIlterThread(Context context, ThreadCallBack callBack) {
        this.context = context;
        this.callBack = callBack;
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

        callBack.returnOpc(filters,context);
    }

    public interface ThreadCallBack
    {
        void returnOpc(ArrayList<Filter> filters,Context context);
    }

}
