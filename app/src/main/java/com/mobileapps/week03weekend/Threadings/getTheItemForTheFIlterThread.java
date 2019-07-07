package com.mobileapps.week03weekend.Threadings;

import android.content.Context;
import android.util.Log;

import com.mobileapps.week03weekend.DataBase.EmployeeDataBaseHelper;

import java.util.HashSet;

public class getTheItemForTheFIlterThread implements Runnable
{
    int opc;
    Context context;
    ThreadCallBack callBack;

    public getTheItemForTheFIlterThread(int opc, Context context, ThreadCallBack callBack) {
        this.opc = opc;
        this.context = context;
        this.callBack = callBack;
    }

    @Override
    public void run()
    {
        EmployeeDataBaseHelper employeeDataBaseHelper = new EmployeeDataBaseHelper(context);
        switch (opc)
        {
            case 0:
                callBack.returnOpc(employeeDataBaseHelper.getAllCity());
                break;
            case 1:
                callBack.returnOpc(employeeDataBaseHelper.getAllState());
                break;
            case 2:
                callBack.returnOpc(employeeDataBaseHelper.getAllZipCode());
                break;
            case 3:
                callBack.returnOpc(employeeDataBaseHelper.getAllPosition());
                break;
            case 4:
                callBack.returnOpc(employeeDataBaseHelper.getAllDepartment());
                break;
        }
        Log.d("Heiner","Getting the hashset of "+opc);
    }

    public interface ThreadCallBack
    {
        void returnOpc(HashSet<String> opc);
    }

}
