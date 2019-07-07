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
        }

        if (fragment != null) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
           // fragmentTransaction.add(R.id.frame_layout,fragment);
            fragmentTransaction.replace(R.id.frame_layout, fragment).addToBackStack(null);
            fragmentTransaction.commit();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
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
