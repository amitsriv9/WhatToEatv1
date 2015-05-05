package com.example.amit.whattoeat.entity;

import com.example.amit.whattoeat.utilities.Enums.*;
import com.example.amit.whattoeat.utilities.YummlyCredentials;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Jun on 4/21/2015.
 */
public class YummlySearchRequest {

//    private static String ENDPOINT = "http://api.yummly.com/v1";
//    private static String APPLICATION_ID = "7a7d2bea";
//    private static String API_KEY = "c178a9fb9e73b844dea1ee9fdc3d02ea";

    private static String ALLERGY_SIGNATURE = "&allowedAllergy[]=";
    private static String COURSE_SIGNATURE = "&allowedCourse[]=";
    private static String PAGE_SIGNATURE = "&start=";
    private static int DEFAULT_PAGE_SIZE = 10;
//    private static String

    private String call = null;
    List<Ingredient> ingredients;
    List<Ingredient> excludedIngredients;
    List<Allergy> allergies;
    List<Course> courses;

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        for(Ingredient i : ingredients) {
            sb.append(i);
        }
        return sb.toString();
    }

    int pageIndex = 0;

    public void nextPage(){
        pageIndex++;
        call = null;
    }

    public void requestPage(int i){
        if(i < 0) {return;}//todo throw exceptions
        else{
            pageIndex = i;
            call = null;
        }
    }
    public void addIngredient(Ingredient in){
        ingredients.add(in);
        call = null;
    }

    public void removeIngredient(Ingredient out){
        excludedIngredients.add(out);
        call = null;
    }

    public void addAllergy(Allergy al){
        allergies.add(al);
        call = null;
    }

    public void addCourse(Course co) {
        courses.add(co);
        call = null;
    }

    public YummlySearchRequest() {
        ingredients = new LinkedList<Ingredient>();
        excludedIngredients = new LinkedList<Ingredient>();
        allergies = new LinkedList<Allergy>();
        courses = new LinkedList<Course>();
    }


    public String buildCall() throws Exception{
        if(call != null){return call;}

        StringBuffer sb = new StringBuffer();
        buildSignature(sb);
        buildIngredients(sb);
        buildAllergies(sb);
        buildCourse(sb);
        buildPageIndex(sb);
        call = sb.toString();
        return call;
    }

    private void buildPageIndex(StringBuffer sb) {
        if(pageIndex == 0) {return;}

        sb.append(PAGE_SIGNATURE);
        sb.append(pageIndex * DEFAULT_PAGE_SIZE);
    }

    private void buildSignature(StringBuffer sb){
        sb.append(YummlyCredentials.ENDPOINT);
        sb.append("/api/recipes?");
        sb.append("_app_id=" + YummlyCredentials.APPLICATION_ID);
        sb.append("&_app_key=" + YummlyCredentials.API_KEY);
        sb.append("&q=");
    }

//    private String buildIngredients() {
//        StringBuffer sb = new StringBuffer();
//        sb.append(ENDPOINT);
//        sb.append("/api/recipes?");
//        sb.append("_app_id=" + APPLICATION_ID);
//        sb.append("&_app_key=" + API_KEY);
//        sb.append("&q=");
//        Iterator<Ingredient> it = ingredients.iterator();
//        Ingredient nextIngredient = it.next();
//        sb.append(nextIngredient.getName());
//        while (it.hasNext()){
//            sb.append("+");
//            nextIngredient = it.next();
//            sb.append(nextIngredient.getName());
//        }
//        System.out.println("built call:" + sb.toString());
//        return sb.toString();
//    }

    private void buildIngredients(StringBuffer sb) {
        if(ingredients.size() == 0) return; //todo throw exception ?
        Iterator<Ingredient> it = ingredients.iterator();
        Ingredient nextIngredient = it.next();
        sb.append(nextIngredient.getName());
        while (it.hasNext()){
            sb.append("+");
            nextIngredient = it.next();
            sb.append(nextIngredient.getName());
        }
    }

    private void buildAllergies(StringBuffer sb) throws Exception{
        for(Allergy al : allergies) {
            sb.append(ALLERGY_SIGNATURE);
            switch (al) {
                case DAIRY:
                    sb.append("396^Dairy-Free");
                    break;
                case EGG:
                    sb.append("397^Egg-Free");
                    break;
                case GLUTEN:
                    sb.append("393^Gluten-Free");
                    break;
                case PEANUT:
                    sb.append("394^Peanut-Free");
                    break;
                case SEAFOOD:
                    sb.append("398^Seafood-Free");
                    break;
                case SESAME:
                    sb.append("399^Sesame-Free");
                    break;
                case SOY:
                    sb.append("400^Soy-Free");
                    break;
                case SULFITE:
                    sb.append("401^Sulfite-Free");
                    break;
                case TREE_NUT:
                    sb.append("395^Tree Nut-Free");
                    break;
                case WHEAT:
                    sb.append("392^Wheat-Free");
                    break;
                default:
                    throw new Exception("unknown allergy");
            }
        }
    }

    private void buildCourse(StringBuffer sb){
        for(Course co : courses){
            sb.append(COURSE_SIGNATURE);
            switch (co) {
                case MAIN_DISHES:
                    sb.append("course^course-Main Dishes");
                    break;
                case DESSERTS:
                    sb.append("course^course-Desserts");
                    break;
                case SIDE_DISHES:
                    sb.append("course^course-Side Dishes");
                    break;
                case LUNCH_AND_SNACKS:
                    sb.append("course^course-Lunch and Snacks");
                    break;
                case APPETIZERS:
                    sb.append("course^course-Appetizers");
                    break;
                case SALADS:
                    sb.append("course^course-Salads");
                    break;
                case BREAKFAST_AND_BRUNCH:
                    sb.append("course^course-Breakfast and Brunch");
                    break;
                case BREADS:
                    sb.append("course^course-Breads");
                    break;
                case SOUPS:
                    sb.append("course^course-Soups");
                    break;
                case BEVERAGES:
                    sb.append("course^course-Beverages");
                    break;
                case CONDIMENTS_AND_SOURCES:
                    sb.append("course^course-Condiments and Sauces");
                    break;
                case COCKTAILS:
                    sb.append("course^course-Cocktails");
                    break;
            }
        }
    }

//    private void
//    public static void setENDPOINT(String ENDPOINT) {
//        YummlyCredentials.ENDPOINT = ENDPOINT;
//    }
//
//    public static void setAPPLICATION_ID(String APPLICATION_ID) {
//        YummlySearchRequest.APPLICATION_ID = APPLICATION_ID;
//    }
//
//    public static void setAPI_KEY(String API_KEY) {
//        YummlySearchRequest.API_KEY = API_KEY;
//    }
}
