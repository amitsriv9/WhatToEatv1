package com.example.amit.whattoeat.boundary;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.content.Intent;

import com.example.amit.whattoeat.R;
import com.example.amit.whattoeat.controller.YummlyFetchThread;
import com.example.amit.whattoeat.controller.YummlyGetThread;
import com.example.amit.whattoeat.entity.Ingredient;
import com.example.amit.whattoeat.entity.User;
import com.example.amit.whattoeat.entity.YummlyGetRequest;
import com.example.amit.whattoeat.entity.YummlyGetResult;
import com.example.amit.whattoeat.entity.YummlySearchRequest;
import com.example.amit.whattoeat.entity.YummlySearchResult;
import com.example.amit.whattoeat.utilities.Enums;

import java.util.LinkedList;
import java.util.List;


public class UserDetails extends ActionBarActivity {
    private Button mNext;
    private EditText age, gender, ht_feet, ht_inches, weight;
    private RadioGroup radioGender;
    private RadioButton radioGenderIs;
    User currentUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        YummlyGetResult result = new YummlyGetResult();
        Thread getterThread = new YummlyGetThread(new YummlyGetRequest("French-Onion-Soup-1019866"), result);
        getterThread.start();
        result.getRecipe();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);

        mNext = (Button) findViewById(R.id.personalDetailsButton);

        mNext.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        age = (EditText)findViewById(R.id.editAge);
                        ht_feet = (EditText)findViewById(R.id.editHeightFeet);
                        ht_inches = (EditText)findViewById(R.id.editHeightInches);
                        weight =  (EditText)findViewById(R.id.editWeight);
                        radioGender = (RadioGroup)findViewById(R.id.radioGroup);

                        int selectedId = radioGender.getCheckedRadioButtonId();
                        radioGenderIs = (RadioButton)findViewById(selectedId);

                        //Jun start 1
                        if(weight.getText().toString().equals("")){
                            Toast.makeText(UserDetails.this, "Please fill in your weight" , Toast.LENGTH_SHORT).show();
                            return;
                        }else if (age.getText().toString().equals("")) {
                            Toast.makeText(UserDetails.this, "Please fill in your age" , Toast.LENGTH_SHORT).show();
                            return;
                        }else if(ht_feet.getText().toString().equals("")){
                            Toast.makeText(UserDetails.this, "Please fill in your height in feets" , Toast.LENGTH_SHORT).show();
                            return;
                        }else if(ht_inches.getText().toString().equals("")){
                            Toast.makeText(UserDetails.this, "Please fill in your height in inches" , Toast.LENGTH_SHORT).show();
                            return;
                        }else if(radioGenderIs == null){
                            Toast.makeText(UserDetails.this, "Please choose your gender" , Toast.LENGTH_SHORT).show();
                            return;
                        }
                        //Jun end 1

                        currentUser = new User(
                                Double.parseDouble(weight.getText().toString()),
                                Integer.parseInt(age.getText().toString()),
                                Long.parseLong(ht_feet.getText().toString()),
                                Long.parseLong(ht_inches.getText().toString()),
                                radioGenderIs.getText().toString());
                        String effingToast = new String("Your BMI is");
                        String effingBMI = String.valueOf(currentUser.getBMI());
                        String theToast = effingToast.concat(effingBMI);
                        CharSequence firstToast = theToast;
                        Toast.makeText(UserDetails.this,firstToast , Toast.LENGTH_SHORT).show();

                        //Jun start 2

                        //Jun end 2
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
