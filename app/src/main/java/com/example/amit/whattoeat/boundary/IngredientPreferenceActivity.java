package com.example.amit.whattoeat.boundary;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.example.amit.whattoeat.R;
import com.example.amit.whattoeat.entity.Ingredient;
import com.example.amit.whattoeat.entity.YummlySearchRequest;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public class IngredientPreferenceActivity extends ActionBarActivity {
    private Button searchButton;
    private ArrayList<String> preferedIngredients;
    public static YummlySearchRequest request;
    private static String CHOOSE_AT_LEAST_ONE = "please choose at least one prefered ingrediet";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ingredient_preferences);
        preferedIngredients = new ArrayList<String>();
        searchButton = (Button) findViewById(R.id.search_recipes);
        searchButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(preferedIngredients.size() == 0) {
                            Toast.makeText(IngredientPreferenceActivity.this, CHOOSE_AT_LEAST_ONE, Toast.LENGTH_SHORT).show();
                            return;
                        }

                        buildSearchRequest();
                        Intent i = new Intent(IngredientPreferenceActivity.this, RecipeListActivity.class);
                        startActivity(i);
                    }
                }
        );
    }


    private void buildSearchRequest(){
//        if()
        if(preferedIngredients == null || preferedIngredients.size() == 0){
            return;
        }

        request = new YummlySearchRequest();
        ArrayList<String> chosenIngrents = chooseIngredientsRandomly(preferedIngredients);
        buildSearchRequest(chosenIngrents, request);
    }

    private void buildSearchRequest(ArrayList<String> chosenIngredients, YummlySearchRequest request) {
        if (chosenIngredients == null || chosenIngredients.size() == 0 || request == null) {
            return;
        }
        for (String i : chosenIngredients) {
            request.addIngredient(new Ingredient(i));
        }
    }

    private ArrayList<String> chooseIngredientsRandomly(ArrayList<String> preferedIngredients) {
        if(preferedIngredients == null) {
            return null;
        }

        ArrayList<String> chosen = new ArrayList<String>();
        if(preferedIngredients.size() <= 3) {
            chosen.addAll(preferedIngredients);
        }else {
            HashSet<Integer> chosenIndex = new HashSet<Integer>();
            Random r = new Random();
            while (chosenIndex.size() < 3 ){
                int i = r.nextInt(preferedIngredients.size());
                if(!chosenIndex.contains(i)) {
                    chosenIndex.add(i);
                }
            }
            for (Integer i : chosenIndex) {
                chosen.add(preferedIngredients.get(i));
            }
        }
        return chosen;
    }

    public void checkboxClicked(View v){
        CheckBox box = (CheckBox) v;
        String ingredient = escapeText(((CheckBox) v).getText());
        if(box.isChecked()) {
            preferedIngredients.add(ingredient);
        }else{
            preferedIngredients.remove(ingredient);
        }
    }

    private String escapeText(CharSequence text){
        if(text == null) return "";
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < text.length(); i ++){
            if(text.charAt(i) !=  ' '){
                sb.append(text.charAt(i));
            }else {
                sb.append("%20");
            }
        }
        return sb.toString();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ingredient_preference, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
