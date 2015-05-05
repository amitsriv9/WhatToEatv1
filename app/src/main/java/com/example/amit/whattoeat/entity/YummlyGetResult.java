package com.example.amit.whattoeat.entity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Jun on 4/23/2015.
 */
public class YummlyGetResult {
    JSONObject attribution;
    JSONArray ingredientLines;
    JSONObject flavors;
    JSONArray nutritions;
    JSONArray images;
    String name;
    int totalTime;
    double rating;
    JSONObject sourceSite;
    JSONObject attributes;

    public JSONObject getAttributes() {
        return attributes;
    }

    public void setAttributes(JSONObject attributes) {
        this.attributes = attributes;
    }

    String id;
    List<String> imagesURLs;
    public YummlyGetResult() {
        rating = -1;
        imagesURLs = new LinkedList<String>();
    }

    public DetailedYummlyRecipe getRecipe() {
//        List<Ingredient> ingredients = new LinkedList<Ingredient>();

        DetailedYummlyRecipe recipe = new DetailedYummlyRecipe(name);
        fillPreparations(recipe);
        fillFlavors(recipe);
        fillOthers(recipe);
        return recipe;
    }

    private void fillPreparations(DetailedYummlyRecipe recipe) {
        if(ingredientLines == null){return;}
        List<String> preparations = recipe.getPreparations();
        for (int i = 0; i < ingredientLines.length(); i++) {
            try {
                preparations.add(ingredientLines.getString(i));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public void fillFlavors(DetailedYummlyRecipe recipe) {
        if(flavors != null) {
            try {
                recipe.setFlavor_sweet(flavors.has(SWEET) ? flavors.getDouble(SWEET) : -1);
                recipe.setFlavor_biter(flavors.has(BITTER) ? flavors.getDouble(BITTER) : -1);
                recipe.setFlavor_meaty(flavors.has(MEATY) ? flavors.getDouble(MEATY) : -1);
                recipe.setFlavor_sour(flavors.has(SOUR) ? flavors.getDouble(SOUR) : -1);
                recipe.setFlavor_piquant(flavors.has(PIQUANT) ? flavors.getDouble(PIQUANT) : -1);
                recipe.setFlavor_salty(flavors.has(SALTY) ? flavors.getDouble(SALTY) : -1);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }else {
            recipe.setFlavor_sweet(-1);
            recipe.setFlavor_biter(-1);
            recipe.setFlavor_meaty(-1);
            recipe.setFlavor_sour(-1);
            recipe.setFlavor_piquant(-1);
            recipe.setFlavor_salty(-1);
        }
    }

    public void fillOthers(DetailedYummlyRecipe recipe) {
        recipe.setRating(rating);
        recipe.setId(id);
        recipe.setSourceDisplayName(name);

        try{
            if(attributes != null && attributes.has(YummlyGetResult.CUISINE) && attributes.getJSONArray(YummlyGetResult.CUISINE).length() > 0){
            recipe.setCuisine(attributes.getJSONArray(YummlyGetResult.CUISINE).getString(0));
            }

            if(images != null){
                recipe.setSmallImageUrls(images.getJSONObject(0).getJSONObject("imageUrlsBySize").getString("360"));
            }

            if(sourceSite != null){
                recipe.setSourcePageUrl(sourceSite.getString("sourceRecipeUrl"));
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        recipe.setTotalTimeInSeconds(totalTime);
//        recipe.setCourse();
//        recipe.set
    }

    public void fillImage(DetailedYummlyRecipe recipe){
        //todo set image here
    }


    public JSONObject getAttribution() {
        return attribution;
    }

    public void setAttribution(JSONObject attribution) {
        this.attribution = attribution;
    }

    public JSONArray getIngredientLines() {
        return ingredientLines;
    }

    public void setIngredientLines(JSONArray ingredientLines) {
        this.ingredientLines = ingredientLines;
    }

    public JSONObject getFlavors() {
        return flavors;
    }

    public void setFlavors(JSONObject flavors) {
        this.flavors = flavors;
    }

    public JSONArray getNutritions() {
        return nutritions;
    }

    public void setNutritions(JSONArray nutritions) {
        this.nutritions = nutritions;
    }

    public JSONArray getImages() {
        return images;
    }

    public void setImages(JSONArray images) {
        this.images = images;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(int totalTime) {
        this.totalTime = totalTime;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public JSONObject getSourceSite() {
        return sourceSite;
    }

    public void setSourceSite(JSONObject sourceSite) {
        this.sourceSite = sourceSite;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public static final String ATTRIBUTION = "attribution"; //Always present in response.
    public static final String ATTRIBUTES = "attributes";
    public static final String COURSE = "course";
    public static final String CUISINE = "cuisine";
    public static final String HOLIDAY = "holiday";
    public static final String SALTY = "Salty";
    public static final String SOUR = "Sour";
    public static final String SWEET = "Sweet";
    public static final String BITTER = "Bitter";
    public static final String MEATY = "Meaty";
    public static final String PIQUANT = "Piquant";
    public static final String RATING = "rating";
    public static final String ID = "id"; //Always present in response.
    public static final String IMAGES = "images";
    public static final String SOURCE_DISPLAY_NAME = "sourceDisplayName";
    public static final String TOTAL_TIME_IN_SECONDS = "totalTimeInSeconds";
    public static final String INGREDIENT_LINES = "ingredientLines"; //Always present in response.
    public static final String RECIPE_NAME = "name"; //Always present in response.
    public static final String FLAVORS = "flavors";
    public static final String NUTRITIONS = "nutritionsEstimates";
    public static final String SOURCE = "source";
}

