package com.example.brom.listviewjsonapp;

/**
 * Created by adamS on 4/17/2018.
 */

public class Dressing {
    public String name;
    public String location;
    public String company;
    public int size;
    public int cost;
    public String auxdata;

    Dressing(String name, String location, String company, int size, int cost, String auxdata){
        this.name = name;
        this.location = location;
        this.company = company;
        this.size = size;
        this.cost = cost;
        this.auxdata = auxdata;
    }

    public String getName(){
        return name;
    }
    public String getLoc() {
        return location;
    }
    public String getCompany() { return company;}
    public int getSize() {return size;}
    public int getCost() {return cost;}
    public String getAuxdata() {return auxdata;}
    public String getText(){
        return name + " is part of the " + location;
    }
}
