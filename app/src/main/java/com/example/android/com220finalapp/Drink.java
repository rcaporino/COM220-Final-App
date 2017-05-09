package com.example.android.com220finalapp;

/**
 * Created by Anthony on 5/8/2017.
 */

public class Drink {

    public int randomNumber;
    private String name;

    public Drink(String name, int a) {
        setName(name);
        randomNumber = a;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return getName();
    }
}

