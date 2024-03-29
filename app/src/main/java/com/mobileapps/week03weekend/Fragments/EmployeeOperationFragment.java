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

public class EmployeeOperationFragment extends Fragment implements View.OnClickListener
{

    private EditText etFirstName, etLastName, etAddress, etCity, etState, etZipCode, etTaxtId, etPosition, etDepartment;
    private Button   btnCreate, btnCancel, btnSave, btnDelete;
    private Context context;
    private Employee employee;
    private MainActivity mainActivity;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_employee_operations, container, false);
        mainActivity = ((MainActivity)getActivity());
        mainActivity.setBack(true);
        context = v.getContext();
        Log.d("Heiner","Starting employe fragment");
        bindViews(v);
        setConfiguration();
        return v;
    }

    private void setConfiguration()
    {
        switch (mainActivity.getEmployeeConfigurationOption())
        {
            case MainActivity.KEY_OPERATION_CREATE:
                mainActivity.setSubMenu(false);
                btnCancel.setVisibility(View.GONE);
                btnSave.setVisibility(View.GONE);
                btnDelete.setVisibility(View.GONE);
                break;
            case MainActivity.KEY_OPERATION_CHECK:
                mainActivity.setSubMenu(true);
                desaibledAllEditText();
                getEmployee();
                btnCancel.setVisibility(View.GONE);
                btnSave.setVisibility(View.GONE);
                btnDelete.setVisibility(View.GONE);
                btnCreate.setVisibility(View.GONE);
                break;
            case MainActivity.KEY_OPERATION_UPDATE:
                mainActivity.setSubMenu(true);
                getEmployee();
                btnCreate.setVisibility(View.GONE);
                 btnDelete.setVisibility(View.GONE);
                 break;
            case MainActivity.KEY_OPERATION_DELETE:
                mainActivity.setSubMenu(true);
                desaibledAllEditText();
                getEmployee();
                btnCreate.setVisibility(View.GONE);
                btnCancel.setVisibility(View.GONE);
                btnSave.setVisibility(View.GONE);
                break;
        }
    }

    private void desaibledAllEditText()
    {
        etFirstName.setEnabled(false);
        etLastName.setEnabled(false);
        etAddress.setEnabled(false);
        etCity.setEnabled(false);
        etState.setEnabled(false);
        etZipCode.setEnabled(false);
        etTaxtId.setEnabled(false);
        etPosition.setEnabled(false);
        etDepartment.setEnabled(false);
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
        btnCancel   = v.findViewById(R.id.btnCancel);
        btnSave     = v.findViewById(R.id.btnSave);
        btnDelete   = v.findViewById(R.id.btnDelete);

        btnCreate.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
        btnSave.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
    }

    private void saveEmployee()
    {
        class SaveEmployeeTask extends AsyncTask<String,String,String>
        {
            @Override
            protected String doInBackground(String... strings) {
                EmployeeDataBaseHelper employeeDataBaseHelper = new EmployeeDataBaseHelper(context);
                employee.setId(mainActivity.getEmployeeId());
                employeeDataBaseHelper.updateEmployeeByID(employee);
                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                Toast.makeText(context, "The employee was updated", Toast.LENGTH_SHORT).show();
                mainActivity.startFragment( new FilterFragment());
            }
        }

        if(areAllTheFieldAreCompleted())
        {
            SaveEmployeeTask saveEmployeeTask = new SaveEmployeeTask();
            saveEmployeeTask.execute();
        }

    }

    private void deleteEmployee()
    {
        class DeleteEmployeTask extends AsyncTask<String,String,String>
        {
            @Override
            protected String doInBackground(String... strings) {
                EmployeeDataBaseHelper employeeDataBaseHelper = new EmployeeDataBaseHelper(context);
                employeeDataBaseHelper.deleteByID(String.valueOf(mainActivity.getEmployeeId()));
                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                Toast.makeText(context, "The employee was deleted", Toast.LENGTH_SHORT).show();
                mainActivity.startFragment( new FilterFragment());
            }
        }
        DeleteEmployeTask deleteEmployeTask = new DeleteEmployeTask();
        deleteEmployeTask.execute();
    }

    public void getEmployee()
    {
        class GetEmployeeTask extends AsyncTask<String,String,String>
        {
            @Override
            protected String doInBackground(String... strings) {
                EmployeeDataBaseHelper employeeDataBaseHelper = new EmployeeDataBaseHelper(context);
                employee = employeeDataBaseHelper.getEmployee(String.valueOf(mainActivity.getEmployeeId()));
                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                etFirstName.setText(employee.getFirstName());
                etLastName.setText(employee.getLastName());
                etAddress.setText(employee.getAddress());
                etCity.setText(employee.getCity());
                etState.setText(employee.getState());
                etZipCode.setText(employee.getZipCode());
                etTaxtId.setText(employee.getTaxId());
                etPosition.setText(employee.getPosition());
                etDepartment.setText(employee.getDepartment());
            }
        }

        GetEmployeeTask getEmployeeTask = new GetEmployeeTask();
        getEmployeeTask.execute();
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
                mainActivity.startFragment( new FilterFragment());

            }
        }

        if(areAllTheFieldAreCompleted())
        {
            CreateEmployeeTask createEmployeeTask = new CreateEmployeeTask();
            createEmployeeTask.execute();
        }
    }

    private boolean areAllTheFieldAreCompleted()
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

        Log.d("Heiner","name "+employee.getFirstName());

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

            case R.id.btnSave:
                saveEmployee();
                break;

            case R.id.btnDelete:
                deleteEmployee();
                break;

        }

    }


}
