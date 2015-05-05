package com.example.amit.whattoeat.entity;

import android.content.Context;
import android.util.Log;

import com.example.amit.whattoeat.controller.YummlyFetchThread;
import com.example.amit.whattoeat.controller.YummlyRecipeGetter;
import com.example.amit.whattoeat.controller.YummlySearchParser;
import com.example.amit.whattoeat.controller.YummlySearcher;
import com.example.amit.whattoeat.entity.DetailedYummlyRecipe;
import com.example.amit.whattoeat.entity.Ingredient;
import com.example.amit.whattoeat.entity.YummlyGetRequest;
import com.example.amit.whattoeat.entity.YummlyGetResult;
import com.example.amit.whattoeat.entity.YummlyRecipe;
import com.example.amit.whattoeat.entity.YummlySearchRequest;
import com.example.amit.whattoeat.entity.YummlySearchResult;
import com.example.amit.whattoeat.utilities.Enums;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by Jun on 5/1/2015.
 */
public class RecipeLab {
    public static HashMap<String, DetailedYummlyRecipe> detailedYummlyRecipeHashMap = new HashMap<String, DetailedYummlyRecipe>();

    private static HashSet<String> storedSearches = new HashSet<String>();

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

    public static ArrayList<YummlyRecipe> getRecipes(YummlySearchRequest request) {
        YummlySearchResult result = new YummlySearchResult();
        new YummlySearcher().fetchByRequest(request, result);
        ArrayList<YummlyRecipe> recipes = result.getRecipes();
        return recipes;
    }

    public static ArrayList<YummlyRecipe> getRecipes(YummlySearchRequest request, Context context) {
        if(hasStoredSearch(context, request.toString())){
            return getStoredRecipes(request, context);
        }

        YummlySearchResult result = new YummlySearchResult();
        new YummlySearcher(context).fetchByRequest(request, result);
        ArrayList<YummlyRecipe> recipes = result.getRecipes();
        if(recipes != null && recipes.size() > 0){
            storedSearches.add(request.toString());
        }
        return recipes;
    }

    private static boolean hasStoredSearch(Context context, String searchSignatue) {
        File file = new File(context.getFilesDir(),searchSignatue);
        return file.exists();
    }

    private static ArrayList<YummlyRecipe> getStoredRecipes(YummlySearchRequest request, Context context) {
        String storedJASON = readFromFile(request.toString(), context);
        YummlySearchResult storedResult = new YummlySearchResult();
        new YummlySearchParser(storedResult).parse(storedJASON);
        return storedResult.getRecipes();
    }

    private static String readFromFile(String file, Context context) {

        String ret = "";
        try {
            InputStream inputStream = context.openFileInput(file);

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append(receiveString);
                }

                inputStream.close();
                ret = stringBuilder.toString();
            }
        }
        catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }

        return ret;
    }

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
        YummlyGetResult result = new YummlyGetResult();
        YummlyGetRequest request = new YummlyGetRequest(id);
        new YummlyRecipeGetter().getRecipe(request, result);
        DetailedYummlyRecipe recipe = result.getRecipe();
        detailedYummlyRecipeHashMap.put(id,recipe);
        return recipe;
    }
}
