package com.mobileapps.week03weekend.Activities;

import android.os.Bundle;

import androidx.core.view.GravityCompat;

import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.navigation.NavigationView;
import com.mobileapps.week03weekend.Fragments.EmployeeOperationFragment;
import com.mobileapps.week03weekend.Fragments.FilterFragment;
import com.mobileapps.week03weekend.R;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{


    private Toolbar toolbar;
    private String dataFilter = "";
    private int    opcFilter  = 0 ;
    private int  employeeConfigurationOption = KEY_OPERATION_CHECK;
    private long employeeId = 0;

    public final static int KEY_OPERATION_CREATE = 0;
    public final static int KEY_OPERATION_CHECK  = 2;
    public final static int KEY_OPERATION_UPDATE = 3;
    public final static int KEY_OPERATION_DELETE = 4;

    public int getEmployeeConfigurationOption() {
        return employeeConfigurationOption;
    }

    public void setEmployeeConfigurationOption(int employeeConfigurationOption) {
        this.employeeConfigurationOption = employeeConfigurationOption;
    }

    public long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }

    public String getDataFilter() {
        return dataFilter;
    }

    public void setDataFilter(String data) {
        this.dataFilter = data;
    }

    public int getOpcFilter() {
        return opcFilter;
    }

    public void setOpcFilter(int opc) {
        this.opcFilter = opc;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        NavigationView navigationView = findViewById(R.id.nav_view);

        navigationView.setNavigationItemSelectedListener(this);

        ShowFragment(R.id.nav_filter);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager().popBackStack();
            }
        });

    }

    public void setBack(Boolean flag)
    {
        getSupportActionBar().setDisplayHomeAsUpEnabled(flag);

    }

    private void ShowFragment(int nav_id)
    {
        Log.d("Heiner","Picking the nav");
        Fragment fragment = null;

        switch (nav_id) {
            case R.id.nav_new_user:
                fragment = new EmployeeOperationFragment();
                break;
            case R.id.nav_update:
                fragment = new EmployeeOperationFragment();
                break;
            case R.id.nav_delete:
                fragment = new EmployeeOperationFragment();
                break;
            case R.id.nav_filter:
                fragment = new FilterFragment();
                break;

                default:
                    fragment = new FilterFragment();

        }

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        // fragmentTransaction.add(R.id.frame_layout,fragment);

        Log.d("Heiner","The tag is "+fragment.getTag());
        fragmentTransaction.replace(R.id.frame_layout, fragment).addToBackStack(null);
        fragmentTransaction.commit();
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }

    public void startFragment(Fragment fragment)
    {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        // fragmentTransaction.add(R.id.frame_layout,fragment);

        fragmentTransaction.replace(R.id.frame_layout, fragment).addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item)
    {
        ShowFragment(item.getItemId());


        return true;

    }
}
