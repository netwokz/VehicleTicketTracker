package com.netwokz.vehicletickettracker;

import android.app.Activity;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Random;

/**
 * Created by netwokz on 7/19/2017.
 */

public class GeneratedVehicle extends Activity {

    private static Random rnd = new Random();

    private static int mStockNumber;
    private static String mYear;
    private static String mMake;
    private static String mModel;
    private static String mColor;
    private static Double mHours;
    private static long mDate;

    private static String years[] = {"1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017"};
    private static String makes[] = {"Ford", "Chevy", "Buick", "Dodge", "Chrysler", "Nissan", "Infinity", "Honda", "Toyota"};
    private static String colors[] = {"White", "Ivory", "Light Yellow", "Yellow", "Snow", "Floral White", "Lemon Chiffon", "Cornsilk", "Seashell", "Lavender Blush", "Papaya Whip", "Blanched Almond", "Misty Rose", "Bisque", "Moccasin", "Navajo White", "Peach Puff", "Gold", "Pink", "Light Pink", "Orange", "Light Salmon", "Dark Orange", "Coral", "Hot Pink", "Tomato", "Orange Red", "Deep Pink", "Fuchsia", "Magenta", "Red", "Old Lace", "Light Goldenrod Yellow", "Linen", "Antique White", "Salmon", "Ghost White", "Mint Cream", "White Smoke", "Beige", "Wheat", "Sandy Brown", "Azure", "Honeydew", "Alice Blue", "Khaki", "Light Coral", "Pale Goldenrod", "Violet", "Dark Salmon", "Lavender", "Light Cyan", "Burly Wood", "Plum", "Gainsboro", "Crimson", "Pale Violet Red", "Goldenrod", "Orchid", "Thistle", "Light Grey", "Tan", "Chocolate", "Peru", "Indian Red", "Medium Violet Red", "Silver", "Dark Khaki", "Rosy Brown", "Medium Orchid", "Dark Goldenrod", "Fire Brick", "Powder Blue", "Light Steel Blue", "Pale Turquoise", "Green Yellow", "Light Blue", "Dark Gray", "Brown", "Sienna", "Yellow Green", "Dark Orchid", "Pale Green", "Dark Violet", "Medium Purple", "Light Green", "Dark Sea Green", "Saddle Brown", "Dark Magenta", "Dark Red", "Blue Violet", "Light Sky Blue", "Sky Blue", "Gray", "Olive", "Purple", "Maroon", "Aquamarine", "Chartreuse", "Lawn Green", "Medium Slate Blue", "Light Slate Gray", "Slate Gray", "Olive Drab", "Slate Blue", "Dim Gray", "Medium Aquamarine", "Cornflower Blue", "Cadet Blue", "Dark Olive Green", "Indigo", "Medium Turquoise", "Dark Slate Blue", "Steel Blue", "Royal Blue", "Turquoise", "Medium Sea Green", "Lime Green", "Dark Slate Gray", "Sea Green", "Forest Green", "Light Sea Green", "Dodger Blue", "Midnight Blue", "Aqua", "Cyan", "Spring Green", "Lime", "Medium Spring Green", "Dark Turquoise", "Deep Sky Blue", "Dark Cyan", "Teal", "Green", "Dark Green", "Blue", "Medium Blue", "Dark Blue", "Navy", "Black"};
    private static String numbers = "0123456789";
    private static String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static Vehicle generate() {
        StringBuffer numberBuffer = new StringBuffer(50);
        for (int n = 0; n < 6; n++) {
            numberBuffer.append(numbers.charAt(rnd.nextInt(numbers.length())));
        }
        StringBuffer letterBuffer = new StringBuffer(50);
        for (int n = 0; n < 7; n++) {
            letterBuffer.append(String.valueOf(letters.charAt(rnd.nextInt(letters.length()))));
        }

        mStockNumber = Integer.valueOf(numberBuffer.toString());
        mYear = years[rnd.nextInt((18 - 1)) + 1];
        mMake = makes[rnd.nextInt((9 - 1)) + 1];
        mModel = letterBuffer.toString();
        mColor = colors[rnd.nextInt((140 - 1)) + 1];
        mHours = 0.0 + rnd.nextFloat() * (20.0 - .01);
        mDate = Calendar.getInstance().getTimeInMillis();

        Vehicle mGeneratedVehicle = new Vehicle(mStockNumber, mYear, mMake, mModel, mColor, new DecimalFormat("#0.##").format(mHours), mDate);

        return mGeneratedVehicle;
    }
}
