package com.netwokz.vehicletickettracker;

import android.util.Log;

import java.util.Comparator;


/**
 * Created by netwokz on 7/23/2017.
 */

public class VehicleComparator implements Comparator<Vehicle> {

    final int TODAY = 0;
    final int MONTH = 1;
    final int ALL = 3;
    final int STOCK_NUMBER = 4;
    final int YEAR = 5;
    final int MAKE = 6;
    final int MODEL = 7;
    final int COLOR = 8;
    final int HOURS = 9;
    final int DATE = 10;

    int compareMethod;
    boolean ascending;


    public VehicleComparator(int method, boolean ascending) {
        this.compareMethod = method;
        this.ascending = ascending;
    }

    @Override
    public int compare(Vehicle obj1, Vehicle obj2) {
        Log.d("compare()", "compareMethod: " + compareMethod);
        switch (compareMethod) {
            case STOCK_NUMBER:
                String stock1 = String.valueOf(obj1.mStockNumber);
                String stock2 = String.valueOf(obj2.mStockNumber);
                if (ascending)
                    return stock1.compareTo(stock2);
                else
                    return stock2.compareTo(stock1);
            case YEAR:
                String year1 = String.valueOf(obj1.mYear);
                String year2 = String.valueOf(obj2.mYear);
                return year1.compareTo(year2);
            case MAKE:
                String make1 = obj1.mMake;
                String make2 = obj2.mMake;
                return make1.compareTo(make2);
            case MODEL:
                String model1 = obj1.mModel;
                String model2 = obj2.mModel;
                return model1.compareTo(model2);
            case COLOR:
                String color1 = String.valueOf(obj1.mColor);
                String color2 = String.valueOf(obj2.mColor);
                return color1.compareTo(color2);
            case HOURS:
                String hours1 = obj1.mMake;
                String hours2 = obj2.mMake;
                return hours1.compareTo(hours2);
            case DATE:
                String date1 = String.valueOf(obj1.mDateAdded);
                String date2 = String.valueOf(obj2.mDateAdded);
                return date1.compareTo(date2);
            case TODAY:
                String today1 = String.valueOf(obj1.mDateAdded);
                String today2 = String.valueOf(obj2.mDateAdded);
                if (ascending)
                    return today1.compareTo(today2);
                else
                    return today2.compareTo(today1);
            case MONTH:
                String month1 = String.valueOf(obj1.mDateAdded);
                String month2 = String.valueOf(obj2.mDateAdded);
                if (ascending)
                    return month1.compareTo(month2);
                else
                    return month2.compareTo(month1);
            default:
                String stoc1 = String.valueOf(obj1.mStockNumber);
                String stoc2 = String.valueOf(obj2.mStockNumber);
                return stoc2.compareTo(stoc1);
        }
    }
}
