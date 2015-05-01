package com.example.amit.whattoeat.boundary;



import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


import com.example.amit.whattoeat.R;
import com.example.amit.whattoeat.entity.YummlyRecipe;

import java.util.ArrayList;
import java.util.List;

public class RecipeListFragment extends ListFragment {
    private ArrayList<YummlyRecipe> recipes;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle("test title");

        recipes = RecipeLab.getRecipes();

        RecipeAdapter adapter = new RecipeAdapter(recipes);
        setListAdapter(adapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {

        Log.d("Tag", " was clicked");
    }

    private class RecipeAdapter extends ArrayAdapter<YummlyRecipe> {
        public RecipeAdapter(ArrayList<YummlyRecipe> recipes) {
            super(getActivity(), 0, recipes);
        } @
                  Override
          public View getView(int position, View convertView, ViewGroup parent) {
// If we weren't given a view, inflate one
            if (convertView == null) {
                convertView = getActivity().getLayoutInflater()
                        .inflate(R.layout.list_item_recipe, null);
            } // Configure the view for this Crime
            YummlyRecipe recipe = getItem(position);

            TextView recipeName = (TextView) convertView.findViewById(R.id.recipe_name);
            recipeName.setText(recipe.getName());

            ImageView recipeImg = (ImageView) convertView.findViewById(R.id.recipe_image);
            recipeImg.setImageResource(R.drawable.ic_launcher);

//            TextView titleTextView =
//                    (TextView)convertView.findViewById(R.id.crime_list_item_titleTextView);
//            titleTextView.setText(c.getTitle());
//            TextView dateTextView =
//                    (TextView)convertView.findViewById(R.id.crime_list_item_dateTextView);
//            dateTextView.setText(c.getDate().toString());
//            CheckBox solvedCheckBox =
//                    (CheckBox)convertView.findViewById(R.id.crime_list_item_solvedCheckBox);
//            solvedCheckBox.setChecked(c.isSolved());
            return convertView;
        }
    }
}
