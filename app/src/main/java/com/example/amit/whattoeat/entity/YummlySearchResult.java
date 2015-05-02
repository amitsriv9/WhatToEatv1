package com.example.amit.whattoeat.entity;

import com.example.amit.whattoeat.controller.YummlyIngredientWrapper;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Jun on 4/20/2015.
 */
public class YummlySearchResult {
    JSONObject attribution;
    int totalMatchCount;
    JSONObject facetCounts;
    JSONArray matches;
    JSONObject criteria;

    public ArrayList<YummlyRecipe> getRecipes(){
        ArrayList<YummlyRecipe> recipes = new ArrayList<>();
        int count = matches.length();
        for(int i = 0; i < count; i ++) {
            try{
                JSONObject newReci = matches.getJSONObject(i);

//                YummlyRecipe newYummlyReci = new YummlyRecipe(
//                        newReci.getString(RECIPE_NAME),
//                        new YummlyIngredientWrapper().wrap(newReci.getJSONArray(INGREDIENTS)),
//                        newReci.has(COURSE)? newReci.getString(COURSE): null,
//                        newReci.has(CUISINE) ? newReci.getString(CUISINE) : null,
//                        newReci.has(HOLIDAY) ? newReci.getString(HOLIDAY) : null,
//                        newReci.has(SALTY) ? newReci.getDouble(SALTY): -1.0,
//                        newReci.has(SOUR) ? newReci.getDouble(SOUR): -1.0,
//                        newReci.has(SWEET) ? newReci.getDouble(SWEET) : -1.0,
//                        newReci.has(BITTER) ? newReci.getDouble(BITTER) : -1.0,
//                        newReci.has(MEATY) ? newReci.getDouble(MEATY) : -1.0,
//                        newReci.has(PIQUANT) ? newReci.getDouble(PIQUANT) : -1.0,
//                        newReci.has(RATING) ? newReci.getInt(RATING) : -1,
//                        newReci.has(ID) ? newReci.getString(ID) : null,
//                        newReci.has(SMALL_IMAGE_URLS) ? newReci.getString(SMALL_IMAGE_URLS) : null,
//                        newReci.has(SOURCE_DISPLAY_NAME) ? newReci.getString(SOURCE_DISPLAY_NAME) : null,
//                        newReci.has(TOTAL_TIME_IN_SECONDS) ? newReci.getInt(TOTAL_TIME_IN_SECONDS) : -1);

                YummlyRecipe newYummlyReci = new YummlyRecipe(newReci.getString(RECIPE_NAME), new YummlyIngredientWrapper().wrap(newReci.getJSONArray(INGREDIENTS)));
                fillAttributes(newYummlyReci, newReci);
                fillFlavors(newYummlyReci, newReci);
                fillRest(newYummlyReci, newReci);


                recipes.add(newYummlyReci);
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
        return recipes; //todo parse json array and form a list of recipes.
    }

    private void fillAttributes(YummlyRecipe newYummlyReci, JSONObject newReci){ //Assuming there's only one value for each attribute
        if(newReci.has(ATTRIBUTES)){
            try{
                JSONObject attri = newReci.getJSONObject(ATTRIBUTES);
                newYummlyReci.setCourse(attri.has(COURSE) && attri.getJSONArray(COURSE).length() > 0 ? attri.getJSONArray(COURSE).getString(0): null);
                newYummlyReci.setCuisine(attri.has(CUISINE) && attri.getJSONArray(CUISINE).length() > 0 ? attri.getJSONArray(CUISINE).getString(0) : null);
                newYummlyReci.setHoliday(attri.has(HOLIDAY) && attri.getJSONArray(HOLIDAY).length() > 0 ? attri.getJSONArray(HOLIDAY).getString(0) : null);
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }else{
            newYummlyReci.setCourse(null);
            newYummlyReci.setCuisine(null);
            newYummlyReci.setHoliday(null);
        }
    }

    private void fillFlavors(YummlyRecipe newYummlyReci, JSONObject newReci){
        if(newReci.has(FLAVORS)){
            try{
                JSONObject flavors = newReci.getJSONObject(FLAVORS);
                newYummlyReci.setFlavor_biter(flavors.getDouble(BITTER));
                newYummlyReci.setFlavor_meaty(flavors.getDouble(MEATY));
                newYummlyReci.setFlavor_piquant(flavors.getDouble(PIQUANT));
                newYummlyReci.setFlavor_salty(flavors.getDouble(SALTY));
                newYummlyReci.setFlavor_sour(flavors.getDouble(SOUR));
                newYummlyReci.setFlavor_sweet(flavors.getDouble(SWEET));

            }catch (Exception ex){
                newYummlyReci.setFlavor_biter(-1.0);
                newYummlyReci.setFlavor_meaty(-1.0);
                newYummlyReci.setFlavor_piquant(-1.0);
                newYummlyReci.setFlavor_salty(-1.0);
                newYummlyReci.setFlavor_sour(-1.0);
                newYummlyReci.setFlavor_sweet(-1.0);
            }
        }else{
            newYummlyReci.setFlavor_biter(-1.0);
            newYummlyReci.setFlavor_meaty(-1.0);
            newYummlyReci.setFlavor_piquant(-1.0);
            newYummlyReci.setFlavor_salty(-1.0);
            newYummlyReci.setFlavor_sour(-1.0);
            newYummlyReci.setFlavor_sweet(-1.0);
        }
    }

    private void fillRest(YummlyRecipe newYummlyReci, JSONObject newReci){
        try {
             newYummlyReci.setId(newReci.has(ID) ? newReci.getString(ID) : null);
             newYummlyReci.setSmallImageUrls(newReci.has(SMALL_IMAGE_URLS) ? newReci.getString(SMALL_IMAGE_URLS) : null);
             newYummlyReci.setSourceDisplayName(newReci.has(SOURCE_DISPLAY_NAME) ? newReci.getString(SOURCE_DISPLAY_NAME) : null);
             newYummlyReci.setRating(newReci.has(RATING) ? newReci.getInt(RATING) : -1);
             newYummlyReci.setTotalTimeInSeconds(newReci.has(TOTAL_TIME_IN_SECONDS) ? newReci.getInt(TOTAL_TIME_IN_SECONDS) : -1);
        }catch (Exception ex){
            ex.printStackTrace();
            newYummlyReci.setId(null);
            newYummlyReci.setSmallImageUrls(null);
            newYummlyReci.setSourceDisplayName(null);
            newYummlyReci.setRating(-1);
            newYummlyReci.setTotalTimeInSeconds(-1);
        }
    }

    public JSONObject getAttribution() {
        return attribution;
    }

    public void setAttribution(JSONObject attribution) {
        this.attribution = attribution;
    }

    public int getTotalMatchCount() {
        return totalMatchCount;
    }

    public void setTotalMatchCount(int totalMatchCount) {
        this.totalMatchCount = totalMatchCount;
    }

    public JSONObject getFacetCounts() {
        return facetCounts;
    }

    public void setFacetCounts(JSONObject facetCounts) {
        this.facetCounts = facetCounts;
    }

    public JSONArray getMatches() {
        return matches;
    }

    public void setMatches(JSONArray matches) {
        this.matches = matches;
    }

    public JSONObject getCriteria() {
        return criteria;
    }

    public void setCriteria(JSONObject criteria) {
        this.criteria = criteria;
    }

    public static final String ATTRIBUTION = "attribution"; //Always present in response.
    public static final String TOTAL_MATCH_COUNT = "totalMatchCount";
    public static final String FACET_COUNTS = "facetCounts";
    public static final String MATCHES = "matches";
    public static final String ATTRIBUTES = "attributes";
    public static final String COURSE = "course";
    public static final String CUISINE = "cuisine";
    public static final String HOLIDAY = "holiday";
    public static final String FLAVORS = "flavors";
    public static final String SALTY = "salty";
    public static final String SOUR = "sour";
    public static final String SWEET = "sweet";
    public static final String BITTER = "bitter";
    public static final String MEATY = "meaty";
    public static final String PIQUANT = "piquant";
    public static final String RATING = "rating";
    public static final String ID = "id"; //Always present in response.
    public static final String SMALL_IMAGE_URLS = "smallImageUrls";
    public static final String SOURCE_DISPLAY_NAME = "sourceDisplayName";
    public static final String TOTAL_TIME_IN_SECONDS = "totalTimeInSeconds";
    public static final String INGREDIENTS = "ingredients"; //Always present in response.
    public static final String RECIPE_NAME = "recipeName"; //Always present in response.
    public static final String CRITERIA = "criteria";
}
