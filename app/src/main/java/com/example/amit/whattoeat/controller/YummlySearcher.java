package com.example.amit.whattoeat.controller;

import com.example.amit.whattoeat.entity.Ingredient;
import com.example.amit.whattoeat.entity.Recipe;
import com.example.amit.whattoeat.entity.YummlySearchResult;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Jun on 4/17/2015.
 */
public class YummlySearcher {
    private static final String ENDPOINT = "http://api.yummly.com/v1";
    private static final String APPLICATION_ID = "7a7d2bea";
    private static final String API_KEY = "c178a9fb9e73b844dea1ee9fdc3d02ea";
//    private ServiceResultParser = new YummlyParser();

    List<Recipe> results;

    public YummlySearcher() {}

    public YummlySearcher(List<Recipe> results){
        super(); //todo is this line necessary?
        this.results = results;
    }

    public YummlySearchResult fetchByIngredients(List<Ingredient> ingredients, YummlySearchResult result) {
        String call = buildCall(ingredients);
        System.out.println("built call");
        String serviceBack;
        try {
            serviceBack = getUrlString(call);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        System.out.println("got reply");
//        YummlySearchResult result = new YummlySearchResult();
        new YummlyParser(result).parse(serviceBack);
        return result;
    }

    private String getUrlString(String urlSpec) throws IOException {
//        urlSpec = "http://api.yummly.com/v1/api/recipes?_app_id=7a7d2bea&_app_key=c178a9fb9e73b844dea1ee9fdc3d02ea&q=Onion";//todo delete this just for testing
//                  "http://api.yummly.com/v1/api/recipes?_app_id=7a7d2bea&_app_key=c178a9fb9e73b844dea1ee9fdc3d02ea&q=Onion"
        URL url = new URL(urlSpec);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            InputStream in = connection.getInputStream();
            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) { return null;}
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            StringBuffer sb = new StringBuffer();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            reader.close();
            return sb.toString();
        }finally {
            connection.disconnect();
        }
    }

    public List<Recipe> parseRecipees(String callReturn) {

        System.out.println(callReturn);
        return null;

    }

/*
* */
    private String buildCall(List<Ingredient> ingredients) {
        StringBuffer sb = new StringBuffer();
        sb.append(ENDPOINT);
        sb.append("/api/recipes?");
        sb.append("_app_id=" + APPLICATION_ID);
        sb.append("&_app_key=" + API_KEY);
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
