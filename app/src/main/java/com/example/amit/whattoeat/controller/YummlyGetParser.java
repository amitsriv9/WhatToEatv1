package com.example.amit.whattoeat.controller;

import com.example.amit.whattoeat.entity.DetailedYummlyRecipe;
import com.example.amit.whattoeat.entity.YummlyGetResult;
import com.example.amit.whattoeat.entity.YummlySearchResult;

import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Jun on 4/23/2015.
 */
public class YummlyGetParser implements ServiceResultParser{
    //    DetailedYummlyRecipe recipe;
    YummlyGetResult yummlyResult;

    public YummlyGetParser(YummlyGetResult result) {
        yummlyResult = result;
    }

    @Override
    public List<Object> parse(String callReturn) {
        List<Object> result = new LinkedList<Object>();
        JSONObject json;
        try {
            json = new JSONObject(callReturn);

            yummlyResult.setAttribution(json.getJSONObject(YummlyGetResult.ATTRIBUTION));
            yummlyResult.setId(json.getString(YummlyGetResult.ID));
            yummlyResult.setFlavors(json.getJSONObject(YummlyGetResult.FLAVORS));
            yummlyResult.setImages(json.has(YummlyGetResult.IMAGES) ? json.getJSONArray(YummlyGetResult.IMAGES) : null);
            yummlyResult.setIngredientLines(json.getJSONArray(YummlyGetResult.INGREDIENT_LINES));
            yummlyResult.setName(json.getString(YummlyGetResult.RECIPE_NAME));
            yummlyResult.setNutritions(json.has(YummlyGetResult.NUTRITIONS)?json.getJSONArray(YummlyGetResult.NUTRITIONS):null);
            yummlyResult.setRating(json.getDouble(YummlyGetResult.RATING));
            yummlyResult.setSourceSite(json.has(YummlyGetResult.SOURCE) ? json.getJSONObject(YummlyGetResult.SOURCE) : null);
            yummlyResult.setTotalTime(json.has(YummlyGetResult.TOTAL_TIME_IN_SECONDS) ? json.getInt(YummlyGetResult.TOTAL_TIME_IN_SECONDS) : -1);
            yummlyResult.setAttributes(json.has(YummlyGetResult.ATTRIBUTES) ? json.getJSONObject(YummlyGetResult.ATTRIBUTES): null);
//            yummlyResult.s todo image urls

//            //parsing:
//            yummlyResult.setAttribution(    json.getJSONObject(YummlySearchResult.ATTRIBUTION));
//            yummlyResult.setTotalMatchCount(json.getInt(YummlySearchResult.TOTAL_MATCH_COUNT));
//            yummlyResult.setFacetCounts(    json.getJSONObject(YummlySearchResult.FACET_COUNTS));
//            yummlyResult.setMatches(        json.getJSONArray(YummlySearchResult.MATCHES));
//            yummlyResult.setCriteria(       json.getJSONObject(YummlySearchResult.CRITERIA));
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
//        result.add(yummlyResult.getAttribution());
//        result.add(yummlyResult.getCriteria());
//        result.add(yummlyResult.getFacetCounts());
//        result.add(yummlyResult.getMatches());
//        result.add(yummlyResult.getTotalMatchCount());

        DetailedYummlyRecipe recipe = yummlyResult.getRecipe();
        return  result;
    }


}
