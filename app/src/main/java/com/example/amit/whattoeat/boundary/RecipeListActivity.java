package com.example.amit.whattoeat.boundary;

import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.amit.whattoeat.R;

public class RecipeListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new RecipeListFragment();
    }

}
