package com.example.amit.whattoeat.entity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorWrapper;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Amit on 4/25/2015.
 */
public class RecipeDataBaseHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Recipe";

    private static final String TABLE_RECIPE_LIST = "RecipeList";
    private static final String COLUMN_RECIPE_ID = "_id";
    private static final String COLUMN_RECIPE_NAME = "name";

    private static final String COLUMN_INGREDIENT_1 = "ingredient_1";
    private static final String COLUMN_INGREDIENT_2 = "ingredient_2";
    private static final String COLUMN_INGREDIENT_3 = "ingredient_3";
    private static final String COLUMN_INGREDIENT_4 = "ingredient_4";
    private static final String COLUMN_TASTE = "taste";
    private static final String COLUMN_MEAL_TYPE = "meal_type";

    public RecipeDataBaseHelper(Context newContext){super(newContext, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d("Database operations", "Database created");
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("create table RecipeList(_id integer primary key , name varchar(20), ingredient_1 varchar(20), ingredient_2 varchar(20),ingredient_3 varchar(20),ingredient_4 varchar(20), taste varchar(10), meal_type varchar(10)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public long insertRecipes(ArrayList<Recipe> recipeList ) {

     int i=0; long status=0;
     for(i = 0; i < recipeList.size(); i++) {
         Recipe recipe = recipeList.get(i);
         ContentValues cv = new ContentValues();
         cv.put(COLUMN_RECIPE_ID, recipe.getId());
         cv.put(COLUMN_INGREDIENT_1, recipe.getIng1());
         cv.put(COLUMN_INGREDIENT_2, recipe.getIng2());
         cv.put(COLUMN_INGREDIENT_3, recipe.getIng3());
         cv.put(COLUMN_INGREDIENT_4, recipe.getIng4());
         cv.put(COLUMN_RECIPE_NAME, recipe.getName());
         cv.put(COLUMN_TASTE, recipe.getTaste());
         cv.put(COLUMN_MEAL_TYPE, recipe.getMeal());
         status = getWritableDatabase().insert(TABLE_RECIPE_LIST, null, cv);
     }
        return status;
     }

    public RecipeCursor selectRecipe(String ingredient){
        Cursor wrapped = getReadabledatabase().query(TABLE_RECIPE_LIST,
                null,
                COLUMN_INGREDIENT_1 + "= ?",  // look for a matching recipe ingredient
                ingredient,
                null,
                null,
                null,
                "3");  // limit 3
        return new RecipeCursor(wrapped);
    }
    public static class RecipeCursor extends CursorWrapper {
        public RecipeCursor(Cursor c) {
            super(c);
        }

        public Recipe getRecipe() {
            if( isBeforeFirst() || isAfterLast() ) {
                return null;
            }
            Recipe rec = new Recipe();
            /* rec.setName();
            rec.setIngredients();
            rec.setTaste();
            rec.setMealTime();
            */
            return  rec;
        }
    }


}
