package com.example.amit.whattoeat.controller;

import com.example.amit.whattoeat.entity.DetailedYummlyRecipe;
import com.example.amit.whattoeat.entity.YummlyGetRequest;
import com.example.amit.whattoeat.entity.YummlyGetResult;

/**
 * Created by Jun on 4/22/2015.
 * This class get details of a recipe by calling the get recipe api of yummly with the recipe's ID (from the result of "search" calls)
 */
public class YummlyRecipeGetter {


    public YummlyGetResult getRecipe(YummlyGetRequest request, YummlyGetResult result) {
        String requestCall = request.buildCall();
        String serviceBack;
        try {
            serviceBack = new URLGetter().getUrlString(requestCall);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }

//        YummlyGetResult result = new YummlyGetResult();
        new YummlyGetParser(result).parse(serviceBack);
        return result;
    }
}
