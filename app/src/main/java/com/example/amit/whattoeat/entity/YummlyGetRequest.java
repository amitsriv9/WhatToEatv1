package com.example.amit.whattoeat.entity;

import com.example.amit.whattoeat.utilities.YummlyCredentials;

/**
 * Created by Jun on 4/23/2015.
 */
public class YummlyGetRequest {
    private String recipeId;

    public YummlyGetRequest(String id){
        recipeId = id;
    }

    public String buildCall() {
        StringBuffer sb = new StringBuffer();
        sb.append(YummlyCredentials.ENDPOINT);
        sb.append("/api/recipe/");
        sb.append(recipeId);
        sb.append("?");
        sb.append("_app_id=" + YummlyCredentials.APPLICATION_ID);
        sb.append("&_app_key=" + YummlyCredentials.API_KEY);
        return sb.toString();
    }
}
