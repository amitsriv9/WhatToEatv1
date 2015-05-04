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
    String ingredients;
    String taste;
    String meal_time;
    Long id;

    public Recipe(String name, List<Ingredient> ingredientslist) {
        this.name = name;
        this.ingredients = (String)ingredientslist.get(0).getName();
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
