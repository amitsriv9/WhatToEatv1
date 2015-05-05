package com.example.amit.whattoeat.entity;

import java.util.List;

/**
 * Created by Jun on 4/20/2015.
 */
public class YummlyRecipe extends Recipe{
    private String course;
    private String cuisine;
    private String holiday;
    private double flavor_salty;
    private double flavor_sour;
    private double flavor_sweet;
    private double flavor_biter;
    private double flavor_meaty;
    private double flavor_piquant;
    private double rating;
    private String id;
    private String smallImageUrls;
    private String sourceDisplayName;
    private String sourcePageUrl;

    public String getSourcePageUrl() {
        return sourcePageUrl;
    }

    public void setSourcePageUrl(String sourcePageUrl) {
        this.sourcePageUrl = sourcePageUrl;
    }

    private int totalTimeInSeconds;

    public YummlyRecipe(String name, List<Ingredient> ingredients){super(name, ingredients);}

    public YummlyRecipe(String name) {
        super.getName();
    }

    public YummlyRecipe(String name, List<Ingredient> ingredients, String course, String cuisine, String holiday, double flavor_salty, double flavor_sour, double flavor_sweet, double flavor_biter, double flavor_meaty, double flavor_piquant, int rating, String id, String smallImageUrls, String sourceDisplayName, int totalTimeInSeconds) {
        super(name, ingredients);
        this.course = course;
        this.cuisine = cuisine;
        this.holiday = holiday;
        this.flavor_salty = flavor_salty;
        this.flavor_sour = flavor_sour;
        this.flavor_sweet = flavor_sweet;
        this.flavor_biter = flavor_biter;
        this.flavor_meaty = flavor_meaty;
        this.flavor_piquant = flavor_piquant;
        this.rating = rating;
        this.id = id;
        this.smallImageUrls = smallImageUrls;
        this.sourceDisplayName = sourceDisplayName;
        this.totalTimeInSeconds = totalTimeInSeconds;
    }

    public String getDominantFlavor(){
        String flavor = null;
        if(flavor_salty > 0){flavor = YummlySearchResult.SALTY;}
        if(flavor_sour > 0) {flavor = YummlySearchResult.SOUR ;}
        if(flavor_sweet > 0) {flavor = YummlySearchResult.SWEET ;}
        if(flavor_biter > 0) {flavor = YummlySearchResult.BITTER ;}
        if(flavor_meaty > 0) {flavor = YummlySearchResult.MEATY ;}
        if(flavor_piquant > 0) {flavor = YummlySearchResult.PIQUANT ;}
        return flavor;
    }

    public String getCourse() {
        return course;
    }

    public String getCuisine() {
        return cuisine;
    }

    public String getHoliday() {
        return holiday;
    }

    public double getFlavor_salty() {
        return flavor_salty;
    }

    public double getFlavor_sour() {
        return flavor_sour;
    }

    public double getFlavor_sweet() {
        return flavor_sweet;
    }

    public double getFlavor_biter() {
        return flavor_biter;
    }

    public double getFlavor_meaty() {
        return flavor_meaty;
    }

    public double getFlavor_piquant() {
        return flavor_piquant;
    }

    public double getRating() {
        return rating;
    }

    public String getId() {
        return super.getId();
    }

    public String getSmallImageUrls() {
        return smallImageUrls;
    }

    public String getSourceDisplayName() {
        return sourceDisplayName;
    }

    public int getTotalTimeInSeconds() {
        return totalTimeInSeconds;
    }

    public String getName(){
        return super.getName();
    }

    public String getIngredients(){
        return super.getIngredients();
    }

    public void setTotalTimeInSeconds(int totalTimeInSeconds) {
        this.totalTimeInSeconds = totalTimeInSeconds;
    }

    public void setSourceDisplayName(String sourceDisplayName) {
        this.sourceDisplayName = sourceDisplayName;
    }

    public void setSmallImageUrls(String smallImageUrls) {
        this.smallImageUrls = smallImageUrls;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void setFlavor_piquant(double flavor_piquant) {
        this.flavor_piquant = flavor_piquant;
    }

    public void setFlavor_meaty(double flavor_meaty) {
        this.flavor_meaty = flavor_meaty;
    }

    public void setFlavor_biter(double flavor_biter) {
        this.flavor_biter = flavor_biter;
    }

    public void setFlavor_sweet(double flavor_sweet) {
        this.flavor_sweet = flavor_sweet;
    }

    public void setFlavor_sour(double flavor_sour) {
        this.flavor_sour = flavor_sour;
    }

    public void setFlavor_salty(double flavor_salty) {
        this.flavor_salty = flavor_salty;
    }

    public void setHoliday(String holiday) {
        this.holiday = holiday;
    }

    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }

    public void setCourse(String course) {
        this.course = course;
    }
}
