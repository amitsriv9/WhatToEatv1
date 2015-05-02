package com.example.amit.whattoeat.entity;

/**
 * Created by Amit on 4/15/2015.
 */
public class User {
    // user info to persist here for storage

    int age;
    Long  weight;
    Long  height_feet, height_inches;
    String gender;

    private User(){}

    long claculate_my_bmi(){return (weight/(12*height_feet + height_feet)^2) * 703;}


}
