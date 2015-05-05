package com.example.amit.whattoeat.boundary;



import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


import com.example.amit.whattoeat.R;
import com.example.amit.whattoeat.entity.Ingredient;
import com.example.amit.whattoeat.entity.RecipeLab;
import com.example.amit.whattoeat.controller.ThumbnailDownloader;
import com.example.amit.whattoeat.entity.YummlyRecipe;
import com.example.amit.whattoeat.entity.YummlySearchRequest;

import java.util.ArrayList;

public class RecipeListFragment extends ListFragment {
    public static YummlySearchRequest request;   //TODO move this to UserDetails or some other class

    {
        request = new YummlySearchRequest();
        request.addIngredient(new Ingredient("onion"));
        request.addIngredient(new Ingredient("beef"));
    }

    private ArrayList<YummlyRecipe> recipes = new ArrayList<YummlyRecipe>();
    private ArrayList<String> searchKeywords = new ArrayList<String>();
    public static YummlyRecipe clickedRecipe;
    ThumbnailDownloader<ImageView> mThumbnailThread;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle("test title");

//        recipes = RecipeLab.getRecipes();

        new SearchRecipesTask().execute();


        mThumbnailThread = new ThumbnailDownloader<ImageView>(new Handler()); //JUN ImageView used as identifier of each download
        mThumbnailThread.setListener(new ThumbnailDownloader.Listener<ImageView>() {
            @Override
            public void onThumbnailDownloaded(ImageView imageView, Bitmap thumbnail) {
                if (isVisible()) {
                    imageView.setImageBitmap(thumbnail);
                }
            }
        });
        mThumbnailThread.start();
        mThumbnailThread.getLooper();
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
        clickedRecipe = ((RecipeAdapter) getListAdapter()).getItem(position);
        Intent i = new Intent(getActivity(), RecipeActivity.class);
        startActivity(i);
        //these lines worked ==============
//        Log.d("Tag", " was clicked");
//        YummlyRecipe recipe = ((RecipeAdapter) getListAdapter()).getItem(position);
//        Intent i = new Intent(getActivity(), RecipeActivity.class);
//        i.putExtra(YummlySearchResult.ID, recipe.getName());
//        startActivity(i);
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
                        .inflate(R.layout.list_item_recipe_2, null);
            } // Configure the view for this Crime
            YummlyRecipe recipe = getItem(position);
//            if()
            TextView recipeName = (TextView) convertView.findViewById(R.id.recipe_name);
            recipeName.setTextSize(18);
            recipeName.setText(recipe.getName());

            TextView recipeFlavor = (TextView) convertView.findViewById(R.id.recipe_flavor);
            String flavor = recipe.getDominantFlavor();
            if(flavor != null){
                recipeFlavor.setText("Flavor: " + flavor);
            }

            ImageView recipeImg = (ImageView) convertView.findViewById(R.id.recipe_image);

            if(recipe.getSmallImageUrls() != null){
                Bitmap cacheHit = mThumbnailThread.checkCache(recipe.getSmallImageUrls());
                if (cacheHit != null) {
                    recipeImg.setImageBitmap(cacheHit);
                } else {
                    recipeImg.setImageResource(R.drawable.ic_launcher);
                    mThumbnailThread.queueThumbnail(recipeImg, recipe.getSmallImageUrls());
                }
            }else {
                recipeImg.setImageResource(R.drawable.ic_launcher);
            }



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

    private class SearchRecipesTask extends AsyncTask<Void,Void,ArrayList<YummlyRecipe>> {
        @Override
        protected ArrayList<YummlyRecipe> doInBackground(Void... params) {
            Activity activity = getActivity();
            if (activity == null)
                return new ArrayList<YummlyRecipe>();


            recipes = RecipeLab.getRecipes(RecipeListFragment.request, getActivity().getApplicationContext());//didnt work
//            recipes = RecipeLab.getRecipes(RecipeListFragment.request);//this line works  but doesn't store the result
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

    @Override
    public void onDestroy() {
        super.onDestroy();
        mThumbnailThread.quit(); //JUN kills the downloading thread p424
        Log.i("TAG", "Background thread destroyed");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mThumbnailThread.clearQueue();
    }

}