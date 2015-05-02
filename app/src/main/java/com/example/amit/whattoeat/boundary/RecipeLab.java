package com.example.amit.whattoeat.boundary;

import com.example.amit.whattoeat.controller.YummlyFetchThread;
import com.example.amit.whattoeat.controller.YummlySearcher;
import com.example.amit.whattoeat.entity.DetailedYummlyRecipe;
import com.example.amit.whattoeat.entity.Ingredient;
import com.example.amit.whattoeat.entity.YummlyRecipe;
import com.example.amit.whattoeat.entity.YummlySearchRequest;
import com.example.amit.whattoeat.entity.YummlySearchResult;
import com.example.amit.whattoeat.utilities.Enums;

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

//    public static ArrayList<YummlyRecipe> getRecipes(){
//        YummlySearchResult result = new YummlySearchResult();
//        YummlySearchRequest request = new YummlySearchRequest();
//        request.addIngredient(new Ingredient("onion"));
//        request.addCourse(Enums.Course.SOUPS);
//        request.addAllergy(Enums.Allergy.DAIRY);
//        new YummlyFetchThread(request, result).start(); //todo delete this, just for testing
//        return result.getRecipes();
//    }

    public static ArrayList<YummlyRecipe> getRecipes(ArrayList<String> keywords) {

        YummlySearchResult result = new YummlySearchResult();
        YummlySearchRequest request = new YummlySearchRequest();
        request.addIngredient(new Ingredient("onion"));
        request.addCourse(Enums.Course.SOUPS);
        request.addAllergy(Enums.Allergy.DAIRY);
        new YummlySearcher().fetchByRequest(request, result);
        ArrayList<YummlyRecipe> recipes = result.getRecipes();
        return recipes;

        //these lines worked properly to demonstrate UI update
//        try {
//            Thread.sleep(1500);
//        } catch (InterruptedException ex) {
//
//        }
//        ArrayList<YummlyRecipe> recipes = new ArrayList<YummlyRecipe>();
//        recipes.add(new YummlyRecipe("key one"));
//        recipes.add(new YummlyRecipe("key two"));
//        return recipes;

    }

    public static YummlyRecipe getRecipe(int id) {
        return new YummlyRecipe(String.valueOf(id));
    }

    public static YummlyRecipe getRecipe(String id) {
        return new YummlyRecipe(id);
    }

    public static DetailedYummlyRecipe getDetailedRecipe(String id) {
        return new DetailedYummlyRecipe("detailed" + id);
    }
}
