package com.mobileapps.week03weekend.Models;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Filter
{
    int id;
    String title;
    String picture;
    HashSet<String> opc;

    public List<String> getOpc()
    {
        Log.d("Heiner","Getting the opc from the models ");
        return new ArrayList<>(opc);
    }

    public void setOpc(HashSet<String> opc) {
        this.opc = opc;
    }

    public Filter(int id, String title, String picture) {
        this.id = id;
        this.title = title;
        this.picture = picture;
    }

    public Filter() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public ArrayList<Filter> getAllTheFilter()
    {
        ArrayList<Filter> returnFilters = new ArrayList<>();

        returnFilters.add(new Filter(0,"City"      ,"city"      ));
        returnFilters.add(new Filter(1,"State"     ,"state"     ));
        returnFilters.add(new Filter(2,"Zip Code"  ,"postal"    ));
        returnFilters.add(new Filter(3,"Position"  ,"position"  ));
        returnFilters.add(new Filter(4,"Department","department"));


        return returnFilters;
    }

}
