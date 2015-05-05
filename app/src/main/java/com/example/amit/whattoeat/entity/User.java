package com.example.amit.whattoeat.entity;

import java.io.File;

/**
 * Created by Amit on 4/15/2015.
 */
public class User {
    // user info to persist here for storage

    int age;
    Double  weight;
    Long  height_feet, height_inches;
    String gender;
    double bmi;
    public User(Double wt, int age, Long ht_feet, Long ht_inch, String gen){
        this.weight = wt;
        this.age = age;
        this.height_feet = ht_feet;
        this.height_inches = ht_inch;
        this.bmi = calculate_my_bmi();
        this.gender = gen;
    }

    double calculate_my_bmi(){return (weight.doubleValue()/Math.sqrt(12*height_feet.longValue() + height_inches.longValue())) * 703;} //int value / leads to 0 Jun
    public double getBMI(){return bmi;}

}

