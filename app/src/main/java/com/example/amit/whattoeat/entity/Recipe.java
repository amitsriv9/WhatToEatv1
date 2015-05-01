package com.example.amit.whattoeat.entity;

import java.util.ArrayList;
import java.util.List;

import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by Amit on 4/15/2015.
 */
public class Recipe {
    String name;
    String ingredient_1;
    String ingredient_2;
    String ingredient_3;
    String ingredient_4;
    String taste;
    String meal_time;
    Long id;

     public Recipe(String name) {
        this.name = name;
    }

    public Long getRecipeByID(Long recipeId){
        return id;
    }

    public void setName(String recipeName){
        name  = recipeName;
    }
    public void setIngredients(ArrayList<String> ingredientlist){
        int listsize = 0;
        listsize = ingredientlist.size();

        if (4 == listsize ){
            ingredient_1  =  ingredientlist.get(0);
            ingredient_2  =  ingredientlist.get(1);
            ingredient_3  =  ingredientlist.get(2);
            ingredient_4  =  ingredientlist.get(3);
        }
        else if (3 == listsize ){
            ingredient_1  =  ingredientlist.get(0);
            ingredient_2  =  ingredientlist.get(1);
            ingredient_3  =  ingredientlist.get(2);
        }
        else if (2 == listsize){
            ingredient_1  =  ingredientlist.get(0);
            ingredient_2  =  ingredientlist.get(1);
        }
        else if (1 == listsize){
            ingredient_1  =  ingredientlist.get(0);
        }
    }

    public void setTaste(String flavor){
        taste = flavor;
    }
    public void setMealTime(String meal){
        meal_time  = meal;
    }
    public String getName(){
        return name;
    }
    public String getIng1(){
        return ingredient_1;
    }
    public String getIng2(){
        return ingredient_2;
    }
    public String getIng3(){
        return ingredient_3;
    }
    public String getIng4(){
        return ingredient_4;
    }
    public String getTaste(){
        return taste;
    }
    public String getMealTime(){
        return meal_time;
    }
    public Long getId(){
        return id;
    }
}
