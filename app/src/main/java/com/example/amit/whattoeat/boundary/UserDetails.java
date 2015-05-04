package com.example.amit.whattoeat.boundary;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.content.Intent;

import com.example.amit.whattoeat.R;
import com.example.amit.whattoeat.controller.YummlyFetchThread;
import com.example.amit.whattoeat.controller.YummlyGetThread;
import com.example.amit.whattoeat.entity.Ingredient;
import com.example.amit.whattoeat.entity.YummlyGetRequest;
import com.example.amit.whattoeat.entity.YummlyGetResult;
import com.example.amit.whattoeat.entity.YummlySearchRequest;
import com.example.amit.whattoeat.entity.YummlySearchResult;
import com.example.amit.whattoeat.utilities.Enums;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class UserDetails extends ActionBarActivity {
    //JUN added for communicating with API call

    //JUN added for communicating with API call


private Button mNext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

//        List<Ingredient> ingredients = new LinkedList<Ingredient>();
//        ingredients.add(new Ingredient("onion"));
//
//        YummlySearchResult result = new YummlySearchResult();
//        YummlySearchRequest request = new YummlySearchRequest();
//        request.addIngredient(new Ingredient("onion"));
//        request.addCourse(Enums.Course.SOUPS);
//        request.addAllergy(Enums.Allergy.DAIRY);
//        new YummlyFetchThread(request, result).start(); //todo delete this, just for testing
//        result.getRecipes();

        YummlyGetResult result = new YummlyGetResult();
        Thread getterThread = new YummlyGetThread(new YummlyGetRequest("French-Onion-Soup-1019866"), result);
        getterThread.start();
//        try {
//            Thread.sleep(1000);
//        } catch (Exception ex) {
//
//        }
        result.getRecipe();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);
        mNext = (Button) findViewById(R.id.button);
        mNext = (Button) findViewById(R.id.button);

        mNext.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        Toast.makeText(UserDetails.this, R.string.welcome_toast, Toast.LENGTH_SHORT).show();

                    }
                }
        );
    }

    public void nextScreen(View v){
        Intent nextScreen = new Intent(this,AllPreferences.class);
        startActivity(nextScreen);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_user_details, menu);
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
