package com.example.amit.whattoeat.boundary;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.amit.whattoeat.R;
import com.example.amit.whattoeat.controller.WebFetcher;
import com.example.amit.whattoeat.entity.DetailedYummlyRecipe;
import com.example.amit.whattoeat.entity.RecipeLab;
import com.example.amit.whattoeat.entity.YummlyGetResult;

import java.io.IOException;

public class RecipeFragment extends Fragment {
    private DetailedYummlyRecipe recipe;
    private String imgURL = null;
    public static String sourceURL = null;
    TextView recipeSteps;
    TextView recipeName;
    ImageView recipeImage;
    Bitmap recipeBitmap;
    Button viewSourceButton;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

//    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RecipeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RecipeFragment newInstance(String param1, String param2) {
        RecipeFragment fragment = new RecipeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public static RecipeFragment newInstance(String recipeID){
        RecipeFragment fragment = new RecipeFragment();
        Bundle args = new Bundle();
        args.putString(YummlyGetResult.ID, recipeID);
        fragment.setArguments(args);
        return fragment;
    }

    public RecipeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String recipeID = RecipeListFragment.clickedRecipe.getId();
        new GetRecipesTask().execute();
        //==================
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_recipe_2, container, false);
        if(recipe == null){return v;}

        recipeName = (TextView) v.findViewById(R.id.detailed_recipe_name);
        recipeName.setText(recipe.getName());
        recipeName.setTextSize(20);

        recipeSteps = (TextView) v.findViewById(R.id.detailed_recipe_steps);
        if(recipe.getPreparations() != null && recipe.getPreparations().size() > 0) {
            StringBuffer sb = new StringBuffer();
            for(String s : recipe.getPreparations()){
                sb.append(s + "\n");
            }
            recipeSteps.setText(sb.toString());
        }

        recipeImage = (ImageView) v.findViewById(R.id.detailedRecipe_image);
        if(recipeBitmap == null){
            recipeImage.setImageResource(R.drawable.ic_launcher);
        }else {
            recipeImage.setImageBitmap(recipeBitmap);
        }
        recipeImage.setPadding(0,20,0,20);


        viewSourceButton = (Button) v.findViewById(R.id.view_source);
        viewSourceButton.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        Intent i = new Intent(getActivity(), WebPageActivity.class);
                        startActivity(i);
                    }
                }
        );
        return v;
        //======================================
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_recipe, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
//        try {
//            mListener = (OnFragmentInteractionListener) activity;
//        } catch (ClassCastException e) {
//            throw new ClassCastException(activity.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
//        mListener = null;
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
//    public interface OnFragmentInteractionListener {
//        // TODO: Update argument type and name
//        public void onFragmentInteraction(Uri uri);
//    }

    private class GetRecipesTask extends AsyncTask<Void,Void,DetailedYummlyRecipe> {
        @Override
        protected DetailedYummlyRecipe doInBackground(Void... params) {
            Activity activity = getActivity();
            if (activity == null)
                return null;
            recipe = RecipeLab.getDetailedRecipe(RecipeListFragment.clickedRecipe.getId());
            return recipe;
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
        protected void onPostExecute(DetailedYummlyRecipe recipe) {
            if(recipe == null) {

            } else {

                getFragmentManager().beginTransaction()
                        .detach(RecipeFragment.this)
                        .attach(RecipeFragment.this)
                        .commit();
                imgURL = recipe.getSmallImageUrls();
                if(imgURL != null) {
                    new GetRecipeImgTask().execute();
                }
                sourceURL = recipe.getSourcePageUrl();
//                public void setData(Data data) {
//                    this.data = data;
//                    // The reload fragment code here !
//                    if (this.isDetached()) {
//                        getFragmentManager().beginTransaction()
//                                .detach(this)
//                                .attach(this)
//                                .commit();
//                    }
//                }
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

    private class GetRecipeImgTask extends AsyncTask<Void, Void, Bitmap>{
        @Override
        protected Bitmap doInBackground(Void... params) {
            return getBitmap(imgURL);
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            if(bitmap != null){
                recipeImage.setImageBitmap(bitmap);
            }
        }

        private Bitmap getBitmap(String url) {
            try {
                byte[] bitmapBytes = new WebFetcher().getUrlBytes(url);
                Bitmap bitmapDecode = BitmapFactory
                        .decodeByteArray(bitmapBytes, 0, bitmapBytes.length);
                Log.i("Tag", "bitmap created");
                return bitmapDecode;
            } catch (IOException ioe) {
                Log.e("tag", "Error downloading image", ioe);
            }
            return null;
        }
    }

}