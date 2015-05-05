package com.example.amit.whattoeat.entity;

import java.io.File;

/**
 * Created by Amit on 4/15/2015.
 */
public class User {
    // user info to persist here for storage

    int age;
    Long  weight;
    Long  height_feet, height_inches;
    String gender;
    double bmi;
    public User(Long wt, int age, Long ht_feet, Long ht_inch, String gen){
        this.age = age;
        this.age = age;
        this.height_feet = ht_feet;
        this.height_inches = ht_inch;
        this.bmi = calculate_my_bmi();
        this.gender = gen;
    }

    double calculate_my_bmi(){return (weight/(12*height_feet + height_feet)^2) * 703;}

    saveToFile(){
        String userinfo = "personalisation";
        File user  = open(userinfo,);

    }
}
