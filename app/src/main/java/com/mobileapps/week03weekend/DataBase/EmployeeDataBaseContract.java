package com.mobileapps.week03weekend.DataBase;

import android.database.sqlite.SQLiteOpenHelper;

public class EmployeeDataBaseContract
{
    // Column names...
    public static final String KEY_ID             = "_id";
    public static final String KEY_FIRST_NAME     = "first_name";
    public static final String KEY_LAST_NAME      = "last_name";
    public static final String KEY_STREET_ADDRESS = "street_address";
    public static final String KEY_CITY           = "city";
    public static final String KEY_STATE          = "state";
    public static final String KEY_ZIP_CODE       = "zip_code";
    public static final String KEY_TAX_ID         = "tax_id";
    public static final String KEY_POSITION       = "position";
    public static final String KEY_DEPARTMENT     = "departmen";

    final static String DATABASE_NAME = "employee_table";
    final static int DATABASE_VERSION = 1;

    public static final String EMPLOYEE_TABLE_NAME = "employee_entries";
    public static final String SELECT_ALL_QUERY = String.format("SELECT * FROM %s", EMPLOYEE_TABLE_NAME);


    //query for creating database
    public static final String CELEBRITY_TABLE_CREATE =
            "CREATE TABLE " +
                    EMPLOYEE_TABLE_NAME + " (" +
                    KEY_ID            + " INTEGER PRIMARY KEY, " + // will auto-increment if no value passed
                    KEY_FIRST_NAME    + " TEXT, " +
                    KEY_LAST_NAME     + " TEXT, " +
                    KEY_STREET_ADDRESS + " TEXT, " +
                    KEY_CITY      + " TEXT, " +
                    KEY_STATE  + " TEXT, " +
                    KEY_ZIP_CODE   + " TEXT, " +
                    KEY_TAX_ID       + " TEXT, " +
                    KEY_POSITION       + " TEXT, " +
                    KEY_DEPARTMENT       + " TEXT " +
                    ");";

    public static  String getById(String id)
    {
        return String.format("%s WHERE %S = \"%s\"",
                SELECT_ALL_QUERY,KEY_ID,id);
    }

    public static  String getAllCategory()
    {
        return SELECT_ALL_QUERY;
    }

}
