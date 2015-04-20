package com.example.amit.whattoeat.controller;

import com.example.amit.whattoeat.entity.Ingredient;

import org.json.JSONArray;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Jun on 4/20/2015.
 */
public class YummlyIngredientWrapper {
    public List<Ingredient> wrap(JSONArray json){
        List<Ingredient> ingredients = new LinkedList<Ingredient>();
        for(int i = 0; i < json.length(); i ++){
            try{
                ingredients.add(new Ingredient(json.getString(i)));
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
        return ingredients;
    }
}
