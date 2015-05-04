package com.example.amit.whattoeat.controller;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.example.amit.whattoeat.entity.Ingredient;
import com.example.amit.whattoeat.entity.Recipe;
import com.example.amit.whattoeat.entity.YummlyRecipe;
import com.example.amit.whattoeat.entity.YummlySearchRequest;
import com.example.amit.whattoeat.entity.YummlySearchResult;
import com.example.amit.whattoeat.utilities.YummlyCredentials;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;
//import utilities.YummlyCredentials;

/**
 * Created by Jun on 4/17/2015.
 */
public class YummlySearcher {
//    private static final String ENDPOINT = "http://api.yummly.com/v1";
//    private static final String APPLICATION_ID = "7a7d2bea";
//    private static final String API_KEY = "c178a9fb9e73b844dea1ee9fdc3d02ea";
//    private ServiceResultParser = new YummlyParser();

    private static final int PAGE_NUMBER_FOR_ONE_TIME = 5; //each page contains 10 recipes, 5 pages should be 50

    List<Recipe> results;
    Context context;

    public YummlySearcher(){}

    public YummlySearcher(Context context) {this.context = context;}

    public YummlySearcher(List<Recipe> results){
        super(); //todo is this line necessary?
        this.results = results;
    }

    public YummlySearchResult fetchByIngredients(List<Ingredient> ingredients, YummlySearchResult result) {
        String call = buildCall(ingredients);
        return getYummlySearchResult(result, call, ingredients.toString());
    }

    //todo designed to be called by Amit
    public ArrayList<YummlyRecipe> fetchByIngredientsForDB(List<String> ingredients){
        if(ingredients == null){return new ArrayList<>();}
        YummlySearchRequest request = new YummlySearchRequest();
        for(String s : ingredients){
            request.addIngredient(new Ingredient(s));
        }

        ArrayList<YummlyRecipe> recipes = new ArrayList<YummlyRecipe>();
        for(int i = 0; i < PAGE_NUMBER_FOR_ONE_TIME; i ++){
            request.requestPage(i);
            ArrayList<YummlyRecipe> page = fetchByRequest(request, new YummlySearchResult()).getRecipes();
            if(page != null){recipes.addAll(page);}
        }
        return recipes;
    }

    private YummlySearchResult getYummlySearchResult(YummlySearchResult result, String call, String storeSignature) {
        System.out.println("built call");
        String serviceBack;
        try {
            serviceBack = new URLGetter().getUrlString(call);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        System.out.println("got reply");
        if(context != null){
            storeSearch(storeSignature,serviceBack);
        }
//        YummlySearchResult result = new YummlySearchResult();
        new YummlySearchParser(result).parse(serviceBack);
        return result;
    }

    private boolean storeSearch(String search, String serviceBack){
        try {
            FileWriter out = new FileWriter(new File(context.getFilesDir(), search));
            out.write(serviceBack);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public YummlySearchResult fetchByRequest(YummlySearchRequest request, YummlySearchResult result){
        String call;
        try{
             call = request.buildCall();
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
        return getYummlySearchResult(result, call, request.toString());
    }

//    private String getUrlString(String urlSpec) throws IOException {
////        urlSpec = "http://api.yummly.com/v1/api/recipes?_app_id=7a7d2bea&_app_key=c178a9fb9e73b844dea1ee9fdc3d02ea&q=Onion";//todo delete this just for testing
////                  "http://api.yummly.com/v1/api/recipes?_app_id=7a7d2bea&_app_key=c178a9fb9e73b844dea1ee9fdc3d02ea&q=Onion"
//        URL url = new URL(urlSpec);
//        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//        try {
//            ByteArrayOutputStream out = new ByteArrayOutputStream();
//            InputStream in = connection.getInputStream();
//            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) { return null;}
//            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
//            StringBuffer sb = new StringBuffer();
//            String line;
//            while ((line = reader.readLine()) != null) {
//                sb.append(line);
//            }
//            reader.close();
//            return sb.toString();
//        }finally {
//            connection.disconnect();
//        }
//    }

    public List<Recipe> parseRecipees(String callReturn) {

        System.out.println(callReturn);
        return null;

    }

/*
* */
    private String buildCall(List<Ingredient> ingredients) {
        StringBuffer sb = new StringBuffer();
        sb.append(YummlyCredentials.ENDPOINT);
        sb.append("/api/recipes?");
        sb.append("_app_id=" + YummlyCredentials.APPLICATION_ID);
        sb.append("&_app_key=" + YummlyCredentials.API_KEY);
        sb.append("&q=");
        Iterator<Ingredient> it = ingredients.iterator();
        Ingredient nextIngredient = it.next();
        sb.append(nextIngredient.getName());
        while (it.hasNext()){
            sb.append("+");
            nextIngredient = it.next();
            sb.append(nextIngredient.getName());
        }
        System.out.println("built call:" + sb.toString());
        return sb.toString();
    }

}
