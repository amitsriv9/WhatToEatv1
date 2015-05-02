package com.example.amit.whattoeat.controller;

import com.example.amit.whattoeat.entity.Ingredient;
import com.example.amit.whattoeat.entity.Recipe;
import com.example.amit.whattoeat.entity.YummlyRecipe;
import com.example.amit.whattoeat.entity.YummlySearchRequest;
import com.example.amit.whattoeat.entity.YummlySearchResult;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Jun on 4/17/2015.
 */
public class YummlyFetchThread extends Thread {
    List<Ingredient> ingredients;
    YummlySearchResult result;

    YummlySearchRequest request;

    public YummlyFetchThread(List<Ingredient> ingredients, YummlySearchResult result){
        this.ingredients = ingredients;
        this.result = result;
        request = null;
    }

    public YummlyFetchThread(YummlySearchRequest request, YummlySearchResult result){
        this.request = request;
        this.result = result;
        ingredients = null;
    }
    @Override
    public void run() {

//        List<Ingredient> ingredints = new LinkedList<Ingredient>();
//        ingredints.add(new Ingredient("Onion"));
//        YummlySearchResult result = new YummlySearcher().fetchByIngredients(ingredints, result);
//        List<Recipe> recipes = result.getRecipes();
//        System.out.println("called servie");
        if(ingredients != null) {
            new YummlySearcher().fetchByIngredients(ingredients, result);
            ArrayList<YummlyRecipe> recipes = result.getRecipes();
//            notifyAll();
            System.out.println(recipes);
        }else if(request != null) {
            new YummlySearcher().fetchByRequest(request, result);
            ArrayList<YummlyRecipe> recipes = result.getRecipes();
//            notifyAll();
            System.out.println(recipes);
        }else {
            notifyAll();
            return;
        }
    }
}
