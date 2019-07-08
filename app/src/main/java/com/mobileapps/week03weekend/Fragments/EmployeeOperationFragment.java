package com.mobileapps.week03weekend.Fragments;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.mobileapps.week03weekend.Activities.MainActivity;
import com.mobileapps.week03weekend.DataBase.EmployeeDataBaseHelper;
import com.mobileapps.week03weekend.Models.Employee;
import com.mobileapps.week03weekend.R;
import com.mobileapps.week03weekend.Threadings.SaveTheEmployeeThread;

public class EmployeeOperationFragment extends Fragment implements View.OnClickListener
{

    EditText etFirstName, etLastName, etAddress, etCity, etState, etZipCode, etTaxtId, etPosition, etDepartment;
    Button   btnCreate, btnCancel, btnSave, btnDelete;
    Context context;
    Employee employee;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_employee_operations, container, false);
        ((MainActivity)getActivity()).setBack(true);
        context = v.getContext();
        Log.d("Heiner","Starting employe fragment");
        bindViews(v);
        return v;
    }

    private void bindViews(View v)
    {
        etFirstName = v.findViewById(R.id.etFirstName);
        etLastName  = v.findViewById(R.id.etLasttName);
        etAddress   = v.findViewById(R.id.etAddress);
        etCity      = v.findViewById(R.id.etCity);
        etState     = v.findViewById(R.id.etState);
        etZipCode   = v.findViewById(R.id.etZipCode);
        etTaxtId    = v.findViewById(R.id.etTaxId);
        etPosition  = v.findViewById(R.id.etPosition);
        etDepartment= v.findViewById(R.id.etDepartment);

        btnCreate   = v.findViewById(R.id.btnCreate);
        btnCreate.setOnClickListener(this);

        btnCancel   = v.findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(this);
    }

    public void createEmployee()
    {

        class CreateEmployeeTask extends AsyncTask<String,String,String>
        {

            @Override
            protected String doInBackground(String... strings) {
                EmployeeDataBaseHelper employeeDataBaseHelper = new EmployeeDataBaseHelper(context);
                employeeDataBaseHelper.insertEmployee(employee);
                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                Toast.makeText(context, "The employee was added", Toast.LENGTH_SHORT).show();
                getFragmentManager().popBackStack();
            }
        }

        if(createNewEmployee())
        {
            CreateEmployeeTask createEmployeeTask = new CreateEmployeeTask();
            createEmployeeTask.execute();
        }

        /*if(createNewEmployee())
        {
            SaveTheEmployeeThread saveTheEmployeeThread = new SaveTheEmployeeThread(context,employee,this);
            Thread thread = new Thread(saveTheEmployeeThread);
            thread.start();
        }*/
    }

    private boolean createNewEmployee()
    {

        if(etFirstName.getText().toString().isEmpty())
        {
            Toast.makeText(context, "First name can't be empty", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(etLastName.getText().toString().isEmpty())
        {
            Toast.makeText(context, "Last name can't be empty", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(etAddress.getText().toString().isEmpty())
        {
            Toast.makeText(context, "Address can't be empty", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(etCity.getText().toString().isEmpty())
        {
            Toast.makeText(context, "City can't be empty", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(etState.getText().toString().isEmpty())
        {
            Toast.makeText(context, "State can't be empty", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(etZipCode.getText().toString().isEmpty())
        {
            Toast.makeText(context, "Zip code can't be empty", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(etTaxtId.getText().toString().isEmpty())
        {
            Toast.makeText(context, "Tax Id can't be empty", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(etPosition.getText().toString().isEmpty())
        {
            Toast.makeText(context, "Position can't be empty", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(etDepartment.getText().toString().isEmpty())
        {
            Toast.makeText(context, "Department can't be empty", Toast.LENGTH_SHORT).show();
            return false;
        }

        employee = new Employee(
                etFirstName.getText().toString(),
                etLastName.getText().toString(),
                etAddress.getText().toString(),
                etCity.getText().toString(),
                etState.getText().toString(),
                etZipCode.getText().toString(),
                etTaxtId.getText().toString(),
                etPosition.getText().toString(),
                etDepartment.getText().toString()
                );

        return true;
    }

    public void cancel()
    {
        //Toast.makeText(context, "The employee was added", Toast.LENGTH_SHORT).show();
        getFragmentManager().popBackStack();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle("Employee ");
    }

    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.btnCreate:
            createEmployee();
            break;

            case R.id.btnCancel:
                cancel();
                break;
        }

    }

   /* @Override
    public void returnDone()
    {
        Toast.makeText(context, "The employee was added", Toast.LENGTH_SHORT).show();
        getFragmentManager().popBackStack();
    }*/
}
