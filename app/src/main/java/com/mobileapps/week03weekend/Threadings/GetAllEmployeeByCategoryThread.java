package com.mobileapps.week03weekend.Threadings;

import android.content.Context;
import android.view.View;

import com.mobileapps.week03weekend.Activities.MainActivity;
import com.mobileapps.week03weekend.Adapters.EmployeeAdapter;
import com.mobileapps.week03weekend.DataBase.EmployeeDataBaseHelper;
import com.mobileapps.week03weekend.Models.Employee;

import java.util.ArrayList;

public class GetAllEmployeeByCategoryThread implements Runnable
{
    GetAllEmployeeByCategoryCallBack callBack;
    int opc;
    String data;
    Context context;
    MainActivity mainActivity;
    View view;

    public GetAllEmployeeByCategoryThread(GetAllEmployeeByCategoryCallBack callBack, int opc, String data, Context context, MainActivity mainActivity, View view) {
        this.callBack = callBack;
        this.opc = opc;
        this.data = data;
        this.context = context;
        this.mainActivity = mainActivity;
        this.view = view;
    }

    @Override
    public void run()
    {
        ArrayList<Employee> employees = new ArrayList<>();
        EmployeeDataBaseHelper employeeDataBaseHelper = new EmployeeDataBaseHelper(context);
        switch (opc)
        {
            case 0:
                employees = employeeDataBaseHelper.getEmployeeByCity(data);
                break;
            case 1:
                employees = employeeDataBaseHelper.getEmployeeByState(data);
                break;
            case 2:
                employees = employeeDataBaseHelper.getEmployeeByZipCode(data);
                break;
            case 3:
                employees = employeeDataBaseHelper.getEmployeeByPosition(data);
                break;
            case 4:
                employees = employeeDataBaseHelper.getEmployeeByDepartment(data);
                break;
        }

        EmployeeAdapter employeeAdapter = new EmployeeAdapter(employees,context,mainActivity);


        callBack.returnEmployeesList(employeeAdapter,context,view);
    }


    public interface GetAllEmployeeByCategoryCallBack
    {
        void returnEmployeesList(EmployeeAdapter employeeAdapter, Context context,View view);
    }

}
