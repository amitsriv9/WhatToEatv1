package com.example.amit.whattoeat.controller;

import android.content.Context;
import android.content.Context;
import android.util.Log;

import com.example.amit.whattoeat.entity.RecipeDataBaseHelper;

/**
 * Created by Amit on 4/15/2015.
 */
public class Reminder {
    private Context appContext;
    private RecipeDataBaseHelper dbHelper;
    private Reminder localReminder;

    private Reminder(Context nAppContext){
        appContext = nAppContext;
        dbHelper = new RecipeDataBaseHelper(appContext);

    }

    public Reminder get(Context c){
        if(localReminder == null){
            localReminder = new Reminder(c.getApplicationContext());
        }
        return localReminder;
    }

    public RecipeCursor getSuggestions(String timeOfDay){ return dbHelper.getRecipeList(timeOfday);}

}
