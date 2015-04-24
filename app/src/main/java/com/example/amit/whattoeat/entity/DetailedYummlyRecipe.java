package com.example.amit.whattoeat.entity;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Jun on 4/22/2015.
 */
public class DetailedYummlyRecipe extends YummlyRecipe {
    List<String> preparations = new LinkedList<String>();

    public DetailedYummlyRecipe(String name, List<Ingredient> ingredients){super(name, ingredients);}

    public DetailedYummlyRecipe(String name){
        super(name);
    }

    public List<String> getPreparations() {
        return preparations;
    }
}
