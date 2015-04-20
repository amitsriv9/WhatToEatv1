package com.example.amit.whattoeat.entity;

import java.util.List;

/**
 * Created by Amit on 4/15/2015.
 */
public class Recipe {
    String name;
    List<Ingredient> ingredients;

    public Recipe(String name, List<Ingredient> ingredients) {
        this.name = name;
        this.ingredients = ingredients;
    }

    public String getName() {
        return name;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }
}
