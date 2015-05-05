package com.example.amit.whattoeat.entity;

import java.util.List;

/**
 * Created by Amit on 4/15/2015.
 */
public class Recipe {
    private static final int INGREDIENTS_LENGTH_LIMIT = 200;
    String name;
    List<Ingredient> ingredients;

    public Recipe(String name, List<Ingredient> ingredients) {
        this.name = name;
        this.ingredients = ingredients;
    }

    public Recipe(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public String getConcatenatedIngredients(){
        if(ingredients == null || ingredients.size() == 0) {return null;}
        StringBuffer sb = new StringBuffer();
        sb.append(ingredients.get(0).getName());
        for(int i = 1; i < ingredients.size(); i++){
            String moreIngredient = ingredients.get(i).getName();
            if(moreIngredient.length() + sb.length() < INGREDIENTS_LENGTH_LIMIT){
                sb.append(",");
                sb.append(moreIngredient);
            }else {
                break;
            }
        }
        return sb.toString();
    }
}
