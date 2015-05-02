package com.example.amit.whattoeat.boundary;



import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


import com.example.amit.whattoeat.R;
import com.example.amit.whattoeat.entity.YummlyRecipe;
import com.example.amit.whattoeat.entity.YummlySearchResult;

import java.util.ArrayList;
import java.util.List;

public class RecipeListFragment extends ListFragment {
    private ArrayList<YummlyRecipe> recipes = new ArrayList<YummlyRecipe>();
    private ArrayList<String> searchKeywords = new ArrayList<String>();
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle("test title");

        recipes = RecipeLab.getRecipes();

        new FetchItemsTask().execute();
//        recipes = null;
//        recipes = new ArrayList<YummlyRecipe>();//todo delete, this line proves that empty recipes would not be problem for adapter
        setupAdapter();
    }

    private void setupAdapter() {
        RecipeAdapter adapter = new RecipeAdapter(recipes);
        setListAdapter(adapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {

        Log.d("Tag", " was clicked");
        YummlyRecipe recipe = ((RecipeAdapter) getListAdapter()).getItem(position);
        Intent i = new Intent(getActivity(), RecipeActivity.class);
        i.putExtra(YummlySearchResult.ID, recipe.getName());
        startActivity(i);
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

    private class FetchItemsTask extends AsyncTask<Void,Void,ArrayList<YummlyRecipe>> {
        @Override
        protected ArrayList<YummlyRecipe> doInBackground(Void... params) {
            Activity activity = getActivity();
            if (activity == null)
                return new ArrayList<YummlyRecipe>();
            recipes = RecipeLab.getRecipes(searchKeywords);
            return recipes;
//            String query = PreferenceManager.getDefaultSharedPreferences(activity)
//                    .getString(FlickrFetchr.PREF_SEARCH_QUERY, null);
//
//            if (query != null) {
//                return new FlickrFetchr().search(query, 1);
//            } else {
//                return new FlickrFetchr().fetchItems(mPage);
//            }
        }

        @Override
        //JUN this is run on main thread -- NOT background thread -- p415
        protected void onPostExecute(ArrayList<YummlyRecipe> recipes) {
            if(recipes == null) {

            } else {
                ((RecipeAdapter)getListAdapter()).notifyDataSetChanged(); //todo wasn't able to update UI on its own
                setupAdapter(); //todo this line successfully updated UI.
            }
//===================================
//            if(mItems == null || refreshing) {
//                mItems = items;
//                refreshing = false;
//                setupAdapter();
//            } else {
//                mItems.addAll(items);
//                ((BaseAdapter)mGridView.getAdapter()).notifyDataSetChanged();
//            }
//            loading = false;
        }
    }
}
