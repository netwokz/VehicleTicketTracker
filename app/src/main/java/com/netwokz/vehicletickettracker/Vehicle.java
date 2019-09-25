package com.netwokz.vehicletickettracker;

import android.support.annotation.NonNull;

import com.orm.SugarRecord;

/**
 * Created by netwokz on 7/19/2017.
 */

public class Vehicle extends SugarRecord implements Comparable<Vehicle> {

    int mStockNumber;
    String mYear;
    String mMake;
    String mModel;
    String mColor;
    String mHours;

    long mDateAdded;

    public Vehicle() {

    }

    public Vehicle(int stock, String year, String make, String model, String color, String hours, long dateAdded) {
        this.mStockNumber = stock;
        this.mYear = year;
        this.mMake = make;
        this.mModel = model;
        this.mColor = color;
        this.mHours = hours;
        this.mDateAdded = dateAdded;
    }

    public Vehicle(Vehicle vehicle) {
        this.mStockNumber = vehicle.mStockNumber;
        this.mYear = vehicle.mYear;
        this.mMake = vehicle.mMake;
        this.mModel = vehicle.mModel;
        this.mColor = vehicle.mColor;
        this.mHours = vehicle.mHours;
        this.mDateAdded = vehicle.mDateAdded;
    }


    @Override
    public int compareTo(@NonNull Vehicle vehicle) {
        int stockNum = vehicle.mStockNumber;
        return (int) (this.mStockNumber - stockNum);
    }
}