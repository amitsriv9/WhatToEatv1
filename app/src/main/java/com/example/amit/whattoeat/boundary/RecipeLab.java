package com.example.amit.whattoeat.boundary;

import com.example.amit.whattoeat.entity.DetailedYummlyRecipe;
import com.example.amit.whattoeat.entity.YummlyRecipe;

import java.util.ArrayList;

/**
 * Created by Jun on 5/1/2015.
 */
public class RecipeLab {
    public static ArrayList<YummlyRecipe> getRecipes(){
        ArrayList<YummlyRecipe> recipes = new ArrayList<YummlyRecipe>();
        recipes.add(new YummlyRecipe("one"));
        recipes.add(new YummlyRecipe("two"));
        return  recipes;
    }

    public static YummlyRecipe getRecipe(int id) {
        return new YummlyRecipe(String.valueOf(id));
    }

    public static YummlyRecipe getRecipe(String id) {
        return new YummlyRecipe(id);
    }

    public static DetailedYummlyRecipe getDetailedRecipe(String id) {
        return new DetailedYummlyRecipe(id);
    }
}
