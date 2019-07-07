package com.mobileapps.week03weekend.Threadings;

import android.content.Context;

import com.mobileapps.week03weekend.DataBase.EmployeeDataBaseHelper;
import com.mobileapps.week03weekend.Models.Employee;
import com.mobileapps.week03weekend.Models.Filter;

public class SaveTheEmployeeThread implements Runnable {
    Context context;
    Employee employee;
    SaveTheEmployeeCallBack saveTheEmployeeCallBack;

    public SaveTheEmployeeThread(Context context, Employee employee, SaveTheEmployeeCallBack saveTheEmployeeCallBack) {
        this.context = context;
        this.employee = employee;
        this.saveTheEmployeeCallBack = saveTheEmployeeCallBack;
    }

    @Override
    public void run()
    {
        EmployeeDataBaseHelper employeeDataBaseHelper = new EmployeeDataBaseHelper(context);
        employeeDataBaseHelper.insertEmployee(employee);
        saveTheEmployeeCallBack.returnDone();
    }

    public interface SaveTheEmployeeCallBack
    {
        void returnDone();
    }
}
