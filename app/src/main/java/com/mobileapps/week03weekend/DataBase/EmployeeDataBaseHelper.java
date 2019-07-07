package com.mobileapps.week03weekend.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.mobileapps.week03weekend.Models.Employee;

import java.util.ArrayList;
import java.util.HashSet;

import static com.mobileapps.week03weekend.DataBase.EmployeeDataBaseContract.CELEBRITY_TABLE_CREATE;
import static com.mobileapps.week03weekend.DataBase.EmployeeDataBaseContract.DATABASE_NAME;
import static com.mobileapps.week03weekend.DataBase.EmployeeDataBaseContract.DATABASE_VERSION;
import static com.mobileapps.week03weekend.DataBase.EmployeeDataBaseContract.EMPLOYEE_TABLE_NAME;
import static com.mobileapps.week03weekend.DataBase.EmployeeDataBaseContract.KEY_CITY;
import static com.mobileapps.week03weekend.DataBase.EmployeeDataBaseContract.KEY_DEPARTMENT;
import static com.mobileapps.week03weekend.DataBase.EmployeeDataBaseContract.KEY_FIRST_NAME;
import static com.mobileapps.week03weekend.DataBase.EmployeeDataBaseContract.KEY_ID;
import static com.mobileapps.week03weekend.DataBase.EmployeeDataBaseContract.KEY_LAST_NAME;
import static com.mobileapps.week03weekend.DataBase.EmployeeDataBaseContract.KEY_POSITION;
import static com.mobileapps.week03weekend.DataBase.EmployeeDataBaseContract.KEY_STATE;
import static com.mobileapps.week03weekend.DataBase.EmployeeDataBaseContract.KEY_STREET_ADDRESS;
import static com.mobileapps.week03weekend.DataBase.EmployeeDataBaseContract.KEY_TAX_ID;
import static com.mobileapps.week03weekend.DataBase.EmployeeDataBaseContract.KEY_ZIP_CODE;

public class EmployeeDataBaseHelper  extends SQLiteOpenHelper
{
    String TAG = EmployeeDataBaseHelper.class.getSimpleName();

    public EmployeeDataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d(TAG, "Construct CelebrityDataBaseHelper");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG,"Creating the table ");
        db.execSQL(CELEBRITY_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        Log.d(TAG,"Upgrading database from version " + oldVersion + " to " + newVersion + ", which will destroy all old data");
        if (oldVersion < newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + EMPLOYEE_TABLE_NAME);
            onCreate(db);
        }
    }



    //Insert
    public long insertEmployee(Employee employee)
    {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_FIRST_NAME,     employee.getFirstName());
        contentValues.put(KEY_LAST_NAME,      employee.getLastName());
        contentValues.put(KEY_STREET_ADDRESS, employee.getAddress());
        contentValues.put(KEY_CITY,           employee.getCity());
        contentValues.put(KEY_STATE,          employee.getState());
        contentValues.put(KEY_ZIP_CODE,       employee.getZipCode());
        contentValues.put(KEY_TAX_ID,         employee.getTaxId());
        contentValues.put(KEY_POSITION,       employee.getPosition());
        contentValues.put(KEY_DEPARTMENT,     employee.getDepartment());

        final long id = database.insert(EMPLOYEE_TABLE_NAME, null, contentValues);
        database.close();
        return id;
    }

    //Deletes
    public int deleteByID(String id)
    {
        SQLiteDatabase database = this.getWritableDatabase();
        final int itemsDeleted = database.delete(EMPLOYEE_TABLE_NAME,  KEY_ID + " =? ", new String[]{String.valueOf(id)});
        database.close();
        return itemsDeleted;
    }

    public int deleteByDepartment(String category)
    {
        SQLiteDatabase database = this.getWritableDatabase();
        final int itemsDeleted = database.delete(EMPLOYEE_TABLE_NAME,  KEY_DEPARTMENT + " =? ", new String[]{String.valueOf(category)});
        database.close();
        return itemsDeleted;
    }

    //Updates
    public int updateEmployeeByID(Employee employee)
    {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(KEY_FIRST_NAME,     employee.getFirstName());
        contentValues.put(KEY_LAST_NAME,      employee.getLastName());
        contentValues.put(KEY_STREET_ADDRESS, employee.getAddress());
        contentValues.put(KEY_CITY,           employee.getCity());
        contentValues.put(KEY_STATE,          employee.getState());
        contentValues.put(KEY_ZIP_CODE,       employee.getZipCode());
        contentValues.put(KEY_TAX_ID,         employee.getTaxId());
        contentValues.put(KEY_POSITION,       employee.getPosition());
        contentValues.put(KEY_DEPARTMENT,     employee.getDepartment());

        final int numberOfRowsUpdated = database.update(EMPLOYEE_TABLE_NAME,
                contentValues, // new values to insert
                KEY_ID + " = ?",
                new String[]{String.valueOf(employee.getId())});

        database.close();

        return numberOfRowsUpdated;
    }

    //Count
    public long count()
    {
        SQLiteDatabase database = this.getReadableDatabase();
        final long count = DatabaseUtils.queryNumEntries(database, EMPLOYEE_TABLE_NAME);
        database.close();
        return count;
    }

    //Get()
    public ArrayList<Employee> getAllEmployee() {
        SQLiteDatabase database = this.getReadableDatabase();
        ArrayList<Employee> returnList = new ArrayList<>();

        Cursor cursor = database.rawQuery("SELECT * FROM "+ EMPLOYEE_TABLE_NAME, null);
        if (cursor.moveToFirst()) {
            do {
                String  id                =                      cursor.getString(cursor.getColumnIndex(KEY_ID))                ;
                String  firstName         =                      cursor.getString(cursor.getColumnIndex(KEY_FIRST_NAME))        ;
                String  lastName          =                      cursor.getString(cursor.getColumnIndex(KEY_LAST_NAME))         ;
                String  street            =                      cursor.getString(cursor.getColumnIndex(KEY_STREET_ADDRESS))    ;
                String  city              =                      cursor.getString(cursor.getColumnIndex(KEY_CITY))              ;
                String  state             =                      cursor.getString(cursor.getColumnIndex(KEY_STATE))             ;
                String  zipCode           =                      cursor.getString(cursor.getColumnIndex(KEY_ZIP_CODE))          ;
                String  taxID             =                      cursor.getString(cursor.getColumnIndex(KEY_TAX_ID))            ;
                String  position          =                      cursor.getString(cursor.getColumnIndex(KEY_POSITION))          ;
                String  department        =                      cursor.getString(cursor.getColumnIndex(KEY_DEPARTMENT))        ;

                returnList.add(new Employee(Long.parseLong(id), firstName,lastName,street,city,state,zipCode,taxID,position,department));
            } while (cursor.moveToNext());
        }
        cursor.close();
        database.close();
        return returnList;
    }


    public HashSet<String> getAllCity() {
        SQLiteDatabase database = this.getReadableDatabase();
        HashSet<String> returnList = new HashSet<>();
        Cursor cursor = database.rawQuery(EmployeeDataBaseContract.getAllCategory(),null);
        if (cursor.moveToFirst()) {
            do {
                returnList.add(cursor.getString(cursor.getColumnIndex(KEY_CITY)));
            } while (cursor.moveToNext());
        }
        cursor.close();
        database.close();
        return returnList;
    }

    public HashSet<String> getAllState() {
        SQLiteDatabase database = this.getReadableDatabase();
        HashSet<String> returnList = new HashSet<>();
        Cursor cursor = database.rawQuery(EmployeeDataBaseContract.getAllCategory(),null);
        if (cursor.moveToFirst()) {
            do {
                returnList.add(cursor.getString(cursor.getColumnIndex(KEY_STATE)));
            } while (cursor.moveToNext());
        }
        cursor.close();
        database.close();
        return returnList;
    }

    public HashSet<String> getAllZipCode() {
        SQLiteDatabase database = this.getReadableDatabase();
        HashSet<String> returnList = new HashSet<>();
        Cursor cursor = database.rawQuery(EmployeeDataBaseContract.getAllCategory(),null);
        if (cursor.moveToFirst()) {
            do {
                returnList.add(cursor.getString(cursor.getColumnIndex(KEY_ZIP_CODE)));
            } while (cursor.moveToNext());
        }
        cursor.close();
        database.close();
        return returnList;
    }

    public HashSet<String> getAllPosition() {
        SQLiteDatabase database = this.getReadableDatabase();
        HashSet<String> returnList = new HashSet<>();
        Cursor cursor = database.rawQuery(EmployeeDataBaseContract.getAllCategory(),null);
        if (cursor.moveToFirst()) {
            do {
                returnList.add(cursor.getString(cursor.getColumnIndex(KEY_POSITION)));
            } while (cursor.moveToNext());
        }
        cursor.close();
        database.close();
        return returnList;
    }


     public HashSet<String> getAllDepartment() {
        SQLiteDatabase database = this.getReadableDatabase();
        HashSet<String> returnList = new HashSet<>();
        Cursor cursor = database.rawQuery(EmployeeDataBaseContract.getAllCategory(),null);
        if (cursor.moveToFirst()) {
            do {
                returnList.add(cursor.getString(cursor.getColumnIndex(KEY_DEPARTMENT)));
            } while (cursor.moveToNext());
        }
        cursor.close();
        database.close();
        return returnList;
    }

    public Employee getEmployee(String idToFInd) {

        SQLiteDatabase database = this.getReadableDatabase();
        Employee returnEmployee = new Employee();

        Cursor cursor = database.rawQuery(EmployeeDataBaseContract.getById(idToFInd),null);

        if (cursor.moveToFirst()) {

            String  id                =                      cursor.getString(cursor.getColumnIndex(KEY_ID))                ;
            String  firstName         =                      cursor.getString(cursor.getColumnIndex(KEY_FIRST_NAME))        ;
            String  lastName          =                      cursor.getString(cursor.getColumnIndex(KEY_LAST_NAME))         ;
            String  street            =                      cursor.getString(cursor.getColumnIndex(KEY_STREET_ADDRESS))    ;
            String  city              =                      cursor.getString(cursor.getColumnIndex(KEY_CITY))              ;
            String  state             =                      cursor.getString(cursor.getColumnIndex(KEY_STATE))             ;
            String  zipCode           =                      cursor.getString(cursor.getColumnIndex(KEY_ZIP_CODE))          ;
            String  taxID             =                      cursor.getString(cursor.getColumnIndex(KEY_TAX_ID))            ;
            String  position          =                      cursor.getString(cursor.getColumnIndex(KEY_POSITION))          ;
            String  department        =                      cursor.getString(cursor.getColumnIndex(KEY_DEPARTMENT))        ;

            returnEmployee = new Employee(Long.parseLong(id), firstName,lastName,street,city,state,zipCode,taxID,position,department);
        }
        cursor.close();
        database.close();
        return returnEmployee;
    }






}
