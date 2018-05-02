package com.example.brom.listviewjsonapp;

/**
 * Created by adamS on 4/17/2018.
 */

public class Mountain {
    public String name;
    public String location;
    public int height;
    public String img;

    Mountain(String name, int height, String location, String img){
        this.name = name;
        this.location = location;
        this.height = height;
        this.img = img;
    }

    public String getName(){
        return name;
    }
    public String getLoc() {
        return location;
    }
    public int getHeight() {
        return height;
    }
    public String getImage(){
        return img;
    }
    public String getText(){
        return name + " is part of the " + location + " and is " + height + "m high";
    }
}
